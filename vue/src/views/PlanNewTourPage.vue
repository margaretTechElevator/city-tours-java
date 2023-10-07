<template>
  <body>
    <div v-if="landing" id="container">
      <topper/>
      <google-map id="map" />
      <Route class="route" />

    </div>
   
  </body>
</template>
  
<script>
import Route from "../components/Route.vue";
import GoogleMap from "../components/GoogleMap.vue";
import Topper from '../components/Topper.vue';

export default {
  name: "PlanNewTourPage",
  components: {
    GoogleMap,
    Route,


    Topper
    
  },
  computed: {
    landing() {
      return this.$route.path == "/";
    },
  },
};
</script>
<style scoped>

#map{
  /* resized map because it isnt fitting... something
  about the body and html margin auto is 8px */
  width:96%;
  height:94%;
  z-index: 1;
  position:absolute;
  margin: 0px;
}
#route{
  z-index: 2;

}



</style>