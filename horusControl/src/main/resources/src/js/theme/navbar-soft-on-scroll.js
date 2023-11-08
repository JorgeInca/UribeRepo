const navbarInit = () => {
  const navbar = document.querySelector('[data-navbar-soft-on-scroll]');
  if (navbar) {
    const windowHeight = window.innerHeight;
    const handleAlpha = () => {
      const scrollTop = window.pageYOffset;
      let alpha = (scrollTop / windowHeight) * 2;
      alpha >= 1 && (alpha = 1);
      navbar.style.backgroundColor = `rgba(255, 255, 255, ${alpha})`;
    };
    handleAlpha();
    document.addEventListener('scroll', () => handleAlpha());
  }
};
export default navbarInit;
