import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'

import * as Pinia from 'pinia'
import 'element-plus/dist/index.css'
import '@/assets/cust-elementplus.scss'
import '@/assets/icon/iconfont.css'
import '@/assets/base.scss'

import Layout from '@/components/Layout.vue'

import router from '@/router'
import Utils from '@/utils/Utils.js'
import Verify from '@/utils/Verify.js'
import Message from '@/utils/Message.js'
import Request from '@/utils/Request.js'
import Api from '@/utils/Api.js'

const app = createApp(App);
app.use(ElementPlus)
app.use(Pinia.createPinia())
app.use(router)

app.component('Layout', Layout)

app.config.globalProperties.Utils = Utils;
app.config.globalProperties.Verify = Verify;
app.config.globalProperties.Message = Message;
app.config.globalProperties.Request = Request;
app.config.globalProperties.Api = Api;

app.mount('#app')