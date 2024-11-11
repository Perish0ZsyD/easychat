<!-- 输入vue 生成模板 -->
<template>
  <div class="login-panel">
    <div class="title drag">EasyChat</div>
    <div v-if="showLoading" class="loading-panel">
      <img src="../assets/img/loading.gif" alt="" />
    </div>
    <!-- drag可拖动 -->
    <div v-else class="login-form">
      <div class="error-msg">{{ errorMsg }}</div>
      <el-form ref="formDataRef" :model="formData" label-width="0px" @submit.prevent>
        <!--input输入-->
        <el-form-item prop="email">
          <el-input
            v-model.trim="formData.email"
            size="large"
            clearable
            placeholder="请输入邮箱"
            maxlength="30"
            @focus="cleanVerify"
          >
            <template #prefix>
              <span class="iconfont icon-email"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="!isLogin" prop="nickName">
          <el-input
            v-model.trim="formData.nickName"
            size="large"
            clearable
            placeholder="请输入昵称"
            maxlength="15"
            @focus="cleanVerify"
          >
            <template #prefix>
              <span class="iconfont icon-user-nick"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model.trim="formData.password"
            size="large"
            show-password
            clearable
            placeholder="请输入密码"
            @focus="cleanVerify"
          >
            <template #prefix>
              <span class="iconfont icon-password"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="!isLogin" prop="rePassword">
          <el-input
            v-model.trim="formData.rePassword"
            size="large"
            show-password
            clearable
            placeholder="请再次输入密码"
            @focus="cleanVerify"
          >
            <template #prefix>
              <span class="iconfont icon-password"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="checkcode">
          <div class="check-code-panel">
            <el-input
              v-model.trim="formData.checkCode"
              size="large"
              clearable
              placeholder="请输入验证码"
              @focus="cleanVerify"
            >
              <template #prefix>
                <span class="iconfont icon-checkcode"></span>
              </template>
            </el-input>
            <img :src="checkCodeUrl" class="check-code" @click="changeCheckCode" />
          </div>
        </el-form-item>
        <el-button type="primary" class="login-btn" @click="submit">{{
          isLogin ? '登录' : '注册'
        }}</el-button>
        <div class="bottom-link" @click="changeOpType">
          <!--不能写死,,需要判断-->
          <span class="a-link">{{ isLogin ? '没有账号？' : '已有账号?' }}</span>
          <!-- <span class="a-link">注册</span> -->
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from 'vue'
const { proxy } = getCurrentInstance()
import { useUserInfoStore } from '@/stores/UserInfoStore'
const userInfoStore = useUserInfoStore()

import { useRouter } from 'vue-router'
const router = useRouter()

import md5 from 'js-md5'
const formData = ref({})
const formDataRef = ref()
// const rules = {
//   email: [{ required: true, message: '请输入邮箱' }],
//   password: [{ required: true, message: '请输入密码' }],
//   checkcode: [{ required: true, message: '请输入验证码' }]
// }
// <el-form ref="formDataRef" :model="formData" label-width="0px" @submit.prevent> 去掉了 :ruler="rules"
const isLogin = ref(true) // 判断是登录还是注册
const changeOpType = () => {
  window.ipcRenderer.send('loginOrRegister', !isLogin.value) // 渲染进程,先去改变窗口大小,再传isLogin
  isLogin.value = !isLogin.value
  nextTick(() => {
    // 清理输入
    formDataRef.value.resetFields()
    formData.value = {}
    cleanVerify()
    changeCheckCode() // 切换注册的时候也要切换新的验证码
  })
}

// 获取验证码
const checkCodeUrl = ref(null)
const changeCheckCode = async () => {
  let result = await proxy.Request({
    url: proxy.Api.checkCode
  })
  if (!result) {
    return
  }
  checkCodeUrl.value = result.data.checkCode
  localStorage.setItem('checkCodeKey', result.data.checkCodeKey)
}
changeCheckCode()

const errorMsg = ref(null)
const showLoading = ref(false)
const checkValue = (type, value, msg) => {
  if (proxy.Utils.isEmpty(value)) {
    errorMsg.value = msg
    return false
  }
  if (type && !proxy.Verify[type](value)) {
    errorMsg.value = msg
    return false
  }
  return true
}
const cleanVerify = () => {
  errorMsg.value = ''
}
const submit = async () => {
  cleanVerify()
  if (!checkValue('checkEmail', formData.value.email, '请输入正确的邮箱')) {
    return
  }
  if (!isLogin.value && !checkValue(null, formData.value.nickName, '请输入昵称')) {
    return
  }
  if (
    !checkValue('checkPassword', formData.value.password, '密码只能是数字、字母、特殊字符8~18位')
  ) {
    return
  }
  if (!isLogin.value && formData.value.password != formData.value.rePassword) {
    errorMsg.value = '两次输入的密码不一致'
    return
  }
  if (
    !checkValue(null, formData.value.checkCode, '请输入正确的验证码') // 验证码不需要正则验证
  ) {
    return
  }
  // 做一个判断，如果是登录成功，在加载时显示loading图片，加载完毕转入主页面
  if (isLogin.value) {
    showLoading.value = true
  }
  let result = await proxy.Request({
    url: isLogin.value ? proxy.Api.login : proxy.Api.register,
    showLoading: isLogin.value ? false : true,
    params: {
      email: formData.value.email,
      password: isLogin.value ? md5(formData.value.password) : formData.value.password,
      checkCode: formData.value.checkCode,
      nickName: formData.value.nickName,
      checkCodeKey: localStorage.getItem('checkCodeKey')
    },
    errorCallback: (response) => {
      // 验证码出错的时候,重新获取验证码
      showLoading.value = false
      changeCheckCode()
      errorMsg.value = response.info
    }
  })
  if (!result) {
    return
  }
  if (isLogin.value) {
    userInfoStore.setInfo(result.data)
    localStorage.setItem('token', result.data.token)
    router.push('/main')
    const screeenWidth = window.screen.width
    const screeenHeight = window.screen.height
    window.ipcRenderer.send('openChat', {
      // 主进程交互， websocket进程是在主进程中创建的
      email: formData.value.email,
      token: result.data.token,
      userId: result.data.userId,
      nickName: result.data.nickName,
      admin: result.data.admin,
      screeenWidth: screeenWidth,
      screeenHidth: screeenHeight
    })
  } else {
    proxy.Message.success('登陆成功')
    changeOpType()
  }
}
</script>

<style lang="scss" scoped>
.email-select {
  width: 250px;
}
.loading-panel {
  height: calc(100vh - 32px);
  display: flex;
  justify-self: center;
  align-items: center;
  overflow: hidden;
  img {
    width: 100px;
  }
}
.login-panel {
  background: #fff;
  border-radius: 3px;
  border: 1px solid #ddd;
  .title {
    height: 30px;
    padding: 5px 0px 0px 10px;
  }
  .login-form {
    padding: 0px 15px 29px 15px;
    :deep(.el-input__wrapper) {
      box-shadow: none;
      border-radius: none;
      border: none;
    }
    .el-form-item {
      border-bottom: 1px solid #ddd;
    }
    .email-panel {
      align-items: center;
      width: 100%;
      display: flex;
      .input {
        flex: 1;
      }
      .icon-down {
        margin-left: 3px;
        width: 16px;
        cursor: pointer;
        border: none;
      }
    }
    .check-code-panel {
      display: flex;
      .check-code {
        cursor: pointer;
        width: 120px;
        margin-left: 5px;
      }
    }
    .error-msg {
      color: #f56c6c;
      margin-bottom: 10px;
    }
    .check-code-panel {
      display: flex;
      .check-code {
        cursor: pointer;
        width: 120px;
        margin-left: 5px;
      }
    }
    .login-btn {
      width: 100%;
      margin-top: 20px;
      background: #07c160;
      height: 36px;
      font-size: 16px;
    }
    .bottom-link {
      display: flex;
      justify-content: flex-end;
      margin-top: 20px;
      .a-link {
        color: #409eff;
        cursor: pointer;
      }
    }
  }
}
</style>
