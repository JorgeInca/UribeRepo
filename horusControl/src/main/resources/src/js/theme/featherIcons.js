// import feather from 'feather-icons';
/* -------------------------------------------------------------------------- */
/*                            Feather Icons                                   */
/* -------------------------------------------------------------------------- */

const featherIconsInit = () => {
  if (window.feather) {
    window.feather.replace({
      width: '16px',
      height: '16px'
    });
  }
};

export default featherIconsInit;
