<template>
   <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
    <google-map/>  
  </div>
</template>
<script>
import GoogleMap from './components/GoogleMap.vue';
export default {
  name: 'App',
  components: {
    GoogleMap,  
  }
}
</script>