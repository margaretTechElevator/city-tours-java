<template>
  <div id="cityTourRoute">
    <div
      id="currentList"
      v-for="(location, index) of locations"
      v-bind:key="index"
    >
      <input class="current-inputs" v-model="locations[index]" />
      <button v-on:click="removeFromList(index)" id="removeLocationButton">
        Remove
      </button>
    </div>
<div id="startingFromInputArea">
    <button type="submit" id="magnifyingGlassButton">
      <magnifyingGlass />
    </button>

    <input
      id="addressInputArea"
      v-model="currentInput"
      type="input"
      placeholder="starting from?"
    />
  </div>
    <button v-on:click="addToList" id="addLocationToItineraryButton">
      Add a Stop or "GO!" first stop
    </button>
    <p></p>
    <button v-on:click="generateRoute" id="showRouteButton">show route</button
    ><br /><br />
    <p>Current Locations:</p>
    <button v-on:click="generateRoute">Generate Route</button><br /><br />
  </div>
</template>

<script>
import magnifyingGlass from "../assets/magnifyingGlass.vue";
export default {
  components: { magnifyingGlass },
  name: "Route",
};
</script>

<style scoped>
#startingFromInputArea{
  display: flex;
}
#addressInputArea {
  background: transparent;
  border: none;
}

::placeholder {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
  letter-spacing: 1.8px;
}
#magnifyingGlassButton {
  background: transparent;
  border: none;
}
</style>