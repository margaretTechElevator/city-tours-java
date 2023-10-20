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
    name: "Route"

}
</script>

<style scoped>
#cityTourRoute{
  background: linear-gradient(
    0deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  border-radius: 20px 20px 0 0 ;
  box-shadow: 5px -1px 5px rgba(5, 5, 0, 0.4);

}

</style>