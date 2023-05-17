import { createRouter, createWebHistory } from 'vue-router'
import HomeUserView from '../view/HomeUserView.vue'
import HomeAdminView from '../view/HomeAdminView.vue'
import InsertView from '../view/InsertView.vue'
import UpdateView from '../view/UpdateView.vue'
import LoginView from '../view/LoginView.vue'
// import Login from '../components/Login.vue'
// import Update from '../components/Update.vue'
// import Insert from '../components/Insert.vue'
// import HomeUser from '../components/HomeUser.vue'
// import HomeAdmin from '../components/HomeAdmin.vue'

const router = createRouter({
  //BASE_URL: 'http://ezcloud-demo.com/',
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
        path: '/',
        component: LoginView
    } ,
    {
        path: '/user',
        component: HomeUserView
    },
    {
      path: '/admin',
      component: HomeAdminView
    },
    {
      path: '/admin/insert',
      component: InsertView
    },
    {
      path: '/admin/update/:id',
      component: UpdateView
    }
  ]
})

export default router
