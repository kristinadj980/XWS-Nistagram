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
import SearchPost from '../views/SearchPost.vue'
import SearchPostByLocation from '../views/SearchPostByLocation.vue'
import FriendRequests from '../views/FriendRequests.vue'
import RegisteredUserSearch from '../views/RegisteredUserSearch.vue'
import HomePageAdmin from '../views/HomePageAdmin.vue'
import VerificationRequests from '../views/VerificationRequests.vue'
import Notifications from '../views/Notifications.vue'
import PublicHomepage from '../views/PublicHomepage.vue'
import PublicSearch from '../views/PublicSearch.vue'
import PublicProfiles from '../views/PublicProfiles.vue'
import PublicSearchByTags from '../views/PublicSearchByTags.vue'
import PublicSearchByLocation from '../views/PublicSearchByLocation.vue'

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
    path: '/publicSearchByTags/:selectedTag',
    name: 'PublicSearchByTags',
    component: PublicSearchByTags
  },
  {
    path: '/publicSearchByLocation/:selectedLocation',
    name: 'PublicSearchByLocation',
    component: PublicSearchByLocation
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
  {
    path: '/searchPost/:selectedTag',
    name: 'SearchPost',
    component: SearchPost
  },
  {
    path: '/searchPostByLocation/:selectedLocation',
    name: 'SearchPostByLocation',
    component: SearchPostByLocation
  },
  {
    path: '/registeredUserSearch',
    name: 'RegisteredUserSearch',
    component: RegisteredUserSearch
  },
  {
    path: '/friendRequests',
    name: 'FriendRequests',
    component: FriendRequests
  },
  {
    path: '/homePageAdmin',
    name: 'HomePageAdmin',
    component: HomePageAdmin
  },
  {
    path: '/verificationRequests',
    name: 'VerificationRequests',
    component: VerificationRequests
  },
  {
    path: '/notifications',
    name: 'Notifications',
    component: Notifications
  },
  {
    path: '/publicHomepage',
    name: 'PublicHomepage',
    component: PublicHomepage
  },
  {
    path: '/publicSearch',
    name: 'PublicSearch',
    component: PublicSearch
  },
  {
    path: '/publicProfiles/:selectedUsername',
    name: 'PublicProfiles',
    component: PublicProfiles
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
