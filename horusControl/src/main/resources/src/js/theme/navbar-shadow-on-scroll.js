const navbarShadowOnScrollInit = () => {
  const navbar = document.querySelector('[data-navbar-shadow-on-scroll]');
  if (navbar) {
    window.onscroll = () => {
      if (window.scrollY > 300) {
        navbar.classList.add('navbar-shadow');
      } else {
        navbar.classList.remove('navbar-shadow');
      }
    };
  }
};

export default navbarShadowOnScrollInit;
