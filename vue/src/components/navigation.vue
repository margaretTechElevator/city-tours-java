<template>
  <div id="nav">
    <h1>City Tours</h1>
    <h3>lets plan the perfect daytrip</h3>
    <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    > -->
  </div>
</template>

<script>
export default {
  name: "navigation",
};
</script>

<style>
#nav {
  border: #fa7c47;
  border-width: 10px;
}
h1 {
  text-align: center;
  padding-top: 20px;
  color: #06445b;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 4rem;
  font-weight: 900;
  line-height: 1;
  text-shadow: 1px 1px 35px rgba(130, 114, 110, 0.584);
}
h3 {
  text-align: center;
  color: #d04512;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 700;
  font-size: 1.5rem;
  line-height: 0px;
  padding-bottom: 30px;
  text-shadow: 1px 1px 10px rgba(251, 249, 248, 0.711);
}
</style>