<template>
          <div id="cityTourRoute">
         
        
        <div
          id="currentList"
          v-for="(location, index) of locations"
          v-bind:key="index"
        >
          <input class="current-inputs" v-model="locations[index]" />
          <button v-on:click="removeFromList(index)" id="removeLocationButton">Remove</button>
        </div>
        

        <input v-model="currentInput" type="input" placeholder="address" id="addressInputArea"/>
        <button v-on:click="addToList" id="addLocationToItineraryButton">Add a Stop</button>
        <p> </p>
        <button v-on:click="generateRoute" id="showRouteButton">show route</button><br /><br />
        <p>Current Locations:</p>
        <button v-on:click="generateRoute">Generate Route</button><br /><br />
      </div>
</template>

<script>
export default {
    name: "Route"

}
</script>

<style scoped>

</style>