<template>
  <section class="navContainer">
    <div class="logo-container">
      <div id="pingoLogo">pingo</div>
    </div>

    <input id="menu-toggle" type="checkbox" />
    <label class="menu-button-container" for="menu-toggle">
      <div class="menu-button"></div>
    </label>
    <ul class="menu">
      <li id="newTour"><a href="PlanNewTourPage">new tour</a></li>
    
      <!-- <li><a href="/">home</a></li> -->
      <li><a href="MyToursPage">my tours</a></li>
      <li id="loginLogout">login/logout</li>
      <!-- <li id="login"><a href="Login" >login</a></li>
      <li id="logout"><a href="Logout" >logout</a></li> -->
    </ul>
  </section>
</template>
    
    
<script>
export default {
  name: "Navigation",
};
</script>
    
<style scoped>


.logo-container{
  display: flex;
  align-items: center;
  justify-content: center;
}
#newTour{
  border-radius: 20px 20px 0 0;
}
#login{
 
}
#logout{
  border-radius:  0 0 20px 20px;
}
#loginLogout{
  border-radius:  0 0 20px 20px;
}

#pingoLogo {
 
  font-family: OrelegaOne;
  font-size: 48px;

  -webkit-mask-image: linear-gradient(45deg,#ff0101 25%,rgba(255, 255, 255, 0.2) 50%,#ff0303 75%);
    mask-image: linear-gradient(45deg,#ff0000 25%,rgba(255, 255, 255, 0.2) 50%,#ff0000 75%);
    -webkit-mask-size: 800%;
    mask-size: 800%;
    -webkit-mask-position: 0;
    mask-position: 0;
    transition: mask-position 2s ease,-webkit-mask-position 2s ease;
}
#pingoLogo:hover{
    -webkit-mask-position: 120%;
    mask-position: 120%;
    opacity: 1;
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
  justify-content: center;
  background-color: #182935;
  /* background-color: gray; */
  height: 40px;
  padding-top: 16px;
  padding-bottom:16px;
  /* position: absolute; */
  width: 100%;
  border-radius: 0 0 20px 20px;;
  
}

.menu {
  display: flex;
  flex-direction: column;
  list-style-type: none;
  margin: 0;
  padding: 0;
  z-index: 100;
  align-items: center;
  text-align: center;
}

.menu > li {
 
  overflow: hidden;

}

#menu-toggle {
  display: none;
  z-index:2;
  position:absolute;
  right:15px;
}

.menu-button-container {
  display: block;
  height: 50px;
  width: 50px;
  
  cursor: pointer;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0px;
  right: 16px;
  z-index: 101;
}
.menu-button {
  
  background-color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.50));
  height: 4px;
  width: 30px;
  transition: transform 400ms cubic-bezier(0.23, 1, 0.32, 1);
  border-radius: 2px;
}
#menu-toggle:checked + .menu-button-container .menu-button::before {
  margin-top: 0;
  transform: rotate(45deg); /* Rotate the top bar */
}

#menu-toggle:checked + .menu-button-container .menu-button::after {
  margin-top: 0;
  transform: rotate(-45deg); /* Rotate the bottom bar */
}

.menu {
  display: none;
  position: fixed;
  top: 40px; 
  right: 26px; /* Position 16px from the right of the screen */

}

#menu-toggle:checked ~ .menu {
  display: block; /* Show the menu when the checkbox is checked */
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

@media (max-width: 2700px) {
  .menu-button-container {
    margin-top:16px;
    padding-top: 20px;
  }

  .menu {
    position: absolute;
    top: 0;
      /* move the dropdown up and down */
    margin-top: 80px;
    right: 26px;
    flex-direction: column;
    min-width: 100px;
    max-width: 260px;
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
    height: 2em;
    padding-top:20px;
    padding-bottom: 10px;
    transition: height 400ms cubic-bezier(0.23, 1, 0.32, 1);
    background-color: var(--menu-bar-mint-50, rgba(26, 46, 63, 0.9));
   
  }
}
li {
  vertical-align: super; /* Adjust as needed */
}

</style>