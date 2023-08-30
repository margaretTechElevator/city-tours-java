<template>
   <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>

    <router-view />
    <map-component/>
    
  </div>
  
</template>
<script>
import MapComponent from './components/MapComponent.vue';


export default {
  name: 'App',
  components: {
    MapComponent,
   
  }
}
</script>