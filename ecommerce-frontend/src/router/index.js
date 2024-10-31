import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import categorias from '@/views/categorias.vue'
import clientes from '@/views/clientes.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/categorias',
      name: 'categorias',
      component: categorias
    },
    {
      path: '/clientes',
      name: 'clientes',
      component: clientes
    }
   
  ]
})

export default router
