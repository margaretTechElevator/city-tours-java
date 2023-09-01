<template>
          <div id="cityTourRoute">
         
        <button v-on:click="generateRoute">show route</button><br /><br />
        <div
          id="currentList"
          v-for="(location, index) of locations"
          v-bind:key="index"
        >
          <input class="current-inputs" v-model="locations[index]" />
          <button v-on:click="removeFromList(index)">Remove</button>
        </div>

        <input v-model="currentInput" type="input" placeholder="address" />
        <button v-on:click="addToList">Add a Stop</button>

        <p>Current Locations:</p>
        <button v-on:click="generateRoute">Generate Route</button><br /><br />
      </div>
</template>

<script>
export default {
    name: "route"

}
</script>

<style>

</style>