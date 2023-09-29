<template>
  <!-- this will be a component that is in the nav bar.
    this will change to "logout" when logged in and 
    will have a temporary popup "youre logged out" -->

 
    <!--my account dropdown if you are not logged in -->
    <div id="this">
      <button @click="showDropdown">my account</button>
      <div v-if="showMenu" class="menu">

        <!-- menu items -->
          <input
            type="text"
            placeholder="email"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
          <input
            type="text"
            placeholder="password"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
          <button>login</button>
          <button @click="$router.push('/forgot')">forgot password?</button>

        </div>

<!-- my account if logged in -->
<div id="loggedIn"></div>
<!-- this is a link to the logged in version of the landing page 
so you can get back to that page if you are in my trips page -->
<div a href="Landing">new trip</div>
<div a href="MyToursPage">my trips </div>
</div>


</template>

<script>
export default {
  name: "AccountDropdown",
  data: function () {
    return {
      showMenu: false,
    };
  },
  props: {
    onClick: { type: Function },
  },
  methods: {
    showDropdown: function () {
      this.showMenu = !this.showMenu;
    },
  },
};
</script>

<style scoped>

</style>