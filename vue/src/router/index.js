import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Landing from '../views/Landing.vue'
import MyToursPage from '../views/MyToursPage.vue'
import PlanNewTourPage from '../views/PlanNewTourPage.vue'
import testFlex from '../views/test.vue'
import ForgotPassword from '../views/ForgotPassword.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Landing',
      component: Landing,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "Logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "Register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/myToursPage",
      name: "MyToursPage",
      component: MyToursPage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/planNewTourPage",
      name: "PlanNewTourPage",
      component: PlanNewTourPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/test",
      name: "testFlex",
      component: testFlex,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/forgot",
      name: "forgotPassword",
      component: ForgotPassword,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
