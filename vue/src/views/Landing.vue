<template>
  <body>
    <div id="googleMaps"><Map></Map>/></div>
    <div id="navigation">
      <Topper />
      <Navigation />
    </div>
  </body>
</template>
  
<script>
import Map from "../components/GoogleMap.vue";
import Navigation from "../components/Navigation.vue";
import Topper from "../components/Topper.vue";

export default {
  name: "Landing",
  components: { Navigation, Map, Topper },
};
</script>
  
<style scoped>
#googleMaps {
  background-color: bisque;
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 0;
}

#navigation {
  position: relative;
  z-index: 1;
}
</style>