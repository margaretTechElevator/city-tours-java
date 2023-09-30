<template>
  <body>
    <navigation class="nav"/>
    <h3 id="daytrip">plan the perfect daytrip</h3>
    <div class="container">
    <google-map id="map"/>
    <Route class="route"/>
  </div>
    <!-- <div id="back">
      <Navigation />
      <div v-if="landing" id="tagline">
        <div>your city,</div>
        <div>your adventure,</div>
        <div>perfectly planned.</div>
      </div>
      <google-map />
      <ItineraryList />
      <Route/>
    </div> -->
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue';
import Navigation from '../components/Navigation.vue';
  
  // import GoogleMap from '../components/GoogleMap.vue'
  // import Navigation from '../components/Navigation.vue'
  // import ItineraryList from '../components/ItineraryList.vue'

  export default {
    name: 'PlanNewTourPage',
    components: { 
      GoogleMap, 
      Route,
        Navigation 
      // ItineraryList, 
      // Route 
    },
    computed: {
      landing() {
        return this.$route.path == "/"
      }
    }
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


/* <style>
  #back { 
    background-image: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
  }

  #tagline {
    margin: auto;
    width: 80%;
    text-align: center;
    text-shadow: 1px 1px 15px rgba(215, 255, 53, 0.277);
    font-size: 2.5rem;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-weight: 900;
    color: rgb(1, 37, 105);
    padding-top: 0px;
    padding-top: 20px;
  } */
</style>