import { createRouter, createWebHistory } from 'vue-router'
import HomeUserView from '../view/HomeUserView.vue'
import HomeAdminView from '../view/HomeAdminView.vue'
import InsertView from '../view/InsertView.vue'
import UpdateView from '../view/UpdateView.vue'
import LoginView from '../view/LoginView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
        path: '/',
        name: 'loginView',
        component: LoginView
    },
    {
        path: '/auth/user',
        name: 'homeUserView',
        component: HomeUserView
    },
    {
      path: '/auth/admin',
      name: 'homeAdminView',
      component: HomeAdminView
    },
    {
      path: '/auth/admin/create',
      name: 'insertView',
      component: InsertView
    },
    {
      path: '/auth/admin/update/:id',
      name: 'updateView',
      component: UpdateView
    }
  ]
})

export default router
