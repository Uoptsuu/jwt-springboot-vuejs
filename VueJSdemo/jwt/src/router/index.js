import { createRouter, createWebHistory } from 'vue-router'
import homeUserView from '../view/HomeUserView.vue'
import homeAdminView from '../view/HomeAdminView.vue'
import insertView from '../view/InsertView.vue'
import updateView from '../view/UpdateView.vue'
import loginView from '../view/LoginView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
        path: '/',
        name: 'loginView',
        component: loginView
    },
    {
        path: '/user',
        name: 'homeUserView',
        component: homeUserView
    },
    {
      path: '/admin',
      name: 'homeAdminView',
      component: homeAdminView
    },
    {
      path: '/admin/create',
      name: 'insertView',
      component: insertView
    },
    {
      path: '/admin/update/:id',
      name: 'updateView',
      component: updateView
    }
  ]
})

export default router
