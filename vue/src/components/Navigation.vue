<template>
  <section class="top-nav">
    <div>
     
    </div>
    <input id="menu-toggle" type="checkbox" />
    <label class="menu-button-container" for="menu-toggle">
      <div class="menu-button"></div>
    </label>


    <ul class="menu" id="logged-in-menu">

    
      <li><a href="test">test page</a></li>
      <li><a href="/">landing page</a></li>

      <!-- hide if not logged in -->
      <li><a href="MyToursPage">my trips</a></li>
      <li><a href="PlanNewTourPage">new trip</a></li>
      <!-- <li><a href="Login">login</a></li> -->
      <li><myAccount/></li>
    </ul>


  </section>
</template>
    
    
<script>
import myAccount from './AccountDropdown.vue';

  export default {
  components: { myAccount },
    name: "Navigation",
  };
</script>
    
<style scoped>
  a { 
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
      color:#06445b;
      font-weight: 900;
      text-decoration: none;
      font-size: 1rem;  
  }

  a:hover {
    color: #ffffff;
    text-shadow: 1px 1px 15px rgba(66, 66, 67, 0.252);
  }

  #city {
    padding-top: 30px;
    color: #ffffff;
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    font-size: 4rem;
    font-weight: 900;
    font-size: 2.5rem;
    margin-top: 0px;
    margin-bottom: 0px;
    line-height: 8px;
  }

  .top-nav {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    background-color: transparent;
    /* background: linear-gradient(to left, #f46b45, #eea849); */
    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    color: #fff;
    height: 50px;
    padding: 1em;
  }

  .menu {
    display: flex;
    flex-direction: row;
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  .menu > li {
    margin: 0 1rem;
    overflow: hidden;
  }

  .menu-button-container {
    display: none;
    height: 100%;
    width: 30px;
    cursor: pointer;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  #menu-toggle {
    display: none;
  }

  .menu-button,
  .menu-button::before,
  .menu-button::after {
    display: block;
    background-color: #fff;
    position: absolute;
    height: 4px;
    width: 30px;
    transition: transform 400ms cubic-bezier(0.23, 1, 0.32, 1);
    border-radius: 2px;
  }

  .menu-button::before {
    content: "";
    margin-top: -8px;
  }

  .menu-button::after {
    content: "";
    margin-top: 8px;
  }

  #menu-toggle:checked + .menu-button-container .menu-button::before {
    margin-top: 0px;
    transform: rotate(405deg);
  }

  #menu-toggle:checked + .menu-button-container .menu-button::after {
    margin-top: 0px;
    transform: rotate(-405deg);
  }

  @media (max-width: 700px) {
    .menu-button-container {
      display: flex;
    }

    .menu {
      position: absolute;
      top: 0;
      margin-top: 50px;
      left: 0;
      flex-direction: column;
      width: 100%;
      align-items: end;
    }

    #menu-toggle ~ .menu li {
      height: 0;
      margin: 0;
      padding: 0;
      border: 0;
      transition: height 400ms cubic-bezier(0.23, 1, 0.32, 1);
    }

    #menu-toggle:checked ~ .menu li {
      border: 1px solid #ffffff00;
      height: 1.5em;
      padding: 0.5em;
      transition: height 400ms cubic-bezier(0.23, 1, 0.32, 1);
      background-color:#dbf0d2
    }

    .menu > li {
    
      justify-content:right;
      width: 30%;
      color: white;
    }

    .menu > li:not(:last-child) {
      border-bottom: 1px solid #444;
    }
  }
</style>