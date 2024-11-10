const regs = {
  email: /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/,
  number: /^\+?[1-9][0-9]*$/,
  password: /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z~!@#$%^&*_]{8,}$/,
  version: /^[0-9\.]+$/
}
// element自定义校验规则,生成代码element-plus.org
const verify = (rule, value, regs, callback) => {
  if (value) {
    if (regs.test(value)) {
      callback()
    } else {
      callback(new Error(rule.message))
    }
  } else {
    callback()
  }
}

const checkPassword = (value) => {
  return regs.password.test(value)
}

const checkEmail = (value) => {
  return regs.email.test(value)
}

const password = (rule, value, callback) => {
  return verify(rule, value, regs.password, callback)
}

const number = (rule, value, callback) => {
  return verify(rule, value, regs.number, callback)
}

export default {
  checkPassword,
  checkEmail,
  password,
  number
}