<template>
  <div id="phoneApp">
    <Navigation />

  
    <Topper />
  </div>
</template>

<script>
import Navigation from "../components/Navigation.vue";
import Topper from "../components/Topper.vue";
export default {
  components: { Topper, Navigation },
};
</script>

<style scoped>


</style>