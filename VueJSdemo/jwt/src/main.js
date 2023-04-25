import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'

//import { BootstrapVue } from 'bootstrap-vue'
//import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
//import 'bootstrap/dist/css/bootstrap.css'
//import 'bootstrap-vue/dist/bootstrap-vue.css'

//Vue.use(BootstrapVue)
//Vue.use(IconsPlugin)

//import './assets/app.scss'
//import './assets/main.css'

import './service/Auth.js'

const app = createApp(App)
app.use(router)
app.mount('#app')