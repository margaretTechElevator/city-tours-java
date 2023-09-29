import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

//https://stackoverflow.com/questions/44634193/integrating-google-maps-in-vue-js answer here made the promise based map inititialization work. Would love to know what `window['GoogleMapsInit'] is doing exactly
// It seems like that's creating either an event or a method name that calls the resolve function
export const loadedGoogleMapsAPI = new Promise( (resolve) => {
  window['GoogleMapsInit'] = resolve;

  const mapsApiTag = document.createElement('script')

  mapsApiTag.setAttribute('src',`https://maps.googleapis.com/maps/api/js?key=${process.env.VUE_APP_GMAPS_API_KEY}&libraries=places&callback=GoogleMapsInit`)

  document.body.appendChild(mapsApiTag)
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
