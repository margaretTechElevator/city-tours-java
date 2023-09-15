<template>
  <body>
    <navigation class="nav"/>
    <h3 id="daytrip">plan the perfect daytrip</h3>
    <div class="container">
    <google-map id="map"/>
    <Route class="route"/>
  </div>
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue';
import Navigation from '../components/Navigation.vue';
  

  export default {
    components: { 
      GoogleMap, 
      Route,
        Navigation 
    },
    name: 'PlanNewTourPage',
  }
</script>
<style scoped>

/* .container{
  display: grid;
  grid-template-areas:
  "nav" 
  "map"
  "route"
  ;
}
navigation{
  grid-area: nav;
}
#map{
  grid-area: map;
}
#route{
  grid-area: route;
} */

</style>