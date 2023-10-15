<template>
  <section class="navContainer">
    <div>
      <p id="pingoLogo">pingo</p>
    </div>

    <input id="menu-toggle" type="checkbox" />
    <label class="menu-button-container" for="menu-toggle">
      <div class="menu-button"></div>
    </label>
    <ul class="menu">
      <li id="login"><a href="Login" >login</a></li>
      <li><a href="/">home</a></li>
      <li><a href="MyToursPage">my tours</a></li>
      <li><a href="PlanNewTourPage">new tour</a></li>
      <li id="logout"><a href="Logout" >logout</a></li>
    </ul>
  </section>
</template>
    
    
<script>
export default {
  name: "Navigation",
};
</script>
    
<style scoped>
li a{
  margin-left: 16px;
}
#login{
  border-radius: 20px 20px 0 0;
}
#logout{
  border-radius:  0 0 20px 20px;
}

#pingoLogo {
  font-family: OrelegaOne;
  font-size: 48px;
}

a{
  text-decoration: none;
  color:#CFE7CA;
}
a:hover {
  color: #ADFF00;
  text-shadow: 1px 1px 15px rgba(66, 66, 67, 0.252);
  font-weight: 600;
  font-size: 13px;
}

.navContainer {
  font-family: Inter;
  font-size: 12px;
  color:#CFE7CA;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  background-color: #182935;
  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  height: 50px;
  padding: 16px;
}

.menu {
  display: flex;
  flex-direction: row;
  list-style-type: none;
  margin: 0;
  padding: 0;
  z-index: 100;
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
  background-color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.50));
  position: absolute;
  height: 4px;
  width: 30px;
  transition: transform 400ms cubic-bezier(0.23, 1, 0.32, 1);
  border-radius: 2px;
}
.menu-button:hover,
.menu-button:hover::before,
.menu-button:hover::after {
  background-color: #cfe7ca; /* Change the color on hover */
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
    background-color: var(--menu-bar-mint-50, rgba(26, 46, 63, 0.8));
   
  }

  .menu > li {
    justify-content: right;
    width: 30%;
    color: white;
  }

  .menu > li:not(:last-child) {
    border-bottom: 1px solid #444;
  }
}
</style>