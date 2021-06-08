import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Registration from '../views/Registration.vue'
import Homepage from '../views/Homepage.vue'
import Profile from '../views/Profile.vue'
import AddingPosts from '../views/AddingPosts.vue'
import AddingStories from '../views/AddingStories.vue'
import ProfileInfo from '../views/ProfileInfo.vue'
import GeneralProfiles from '../views/GeneralProfiles.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/homepage',
    name: 'Homepage',
    component: Homepage
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/addingPosts',
    name: 'AddingPosts',
    component: AddingPosts
  },
  {
    path: '/addingStories',
    name: 'AddingStories',
    component: AddingStories
  },
  {
    path: '/profileInfo',
    name: 'ProfileInfo',
    component: ProfileInfo
  },
  {
    path: '/generalProfiles/:selectedUsername',
    name: 'GeneralProfiles',
    component: GeneralProfiles
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
