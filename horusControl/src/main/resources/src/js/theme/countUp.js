/* -------------------------------------------------------------------------- */
/*                                  Count Up                                  */
/* -------------------------------------------------------------------------- */

const countupInit = () => {
  const { getData } = window.phoenix.utils;
  if (window.countUp) {
    const countups = document.querySelectorAll('[data-countup]');
    countups.forEach(node => {
      const { endValue, ...options } = getData(node, 'countup');
      const countUp = new window.countUp.CountUp(node, endValue, {
        duration: 4,
        // suffix: '+',

        ...options
      });
      if (!countUp.error) {
        countUp.start();
      } else {
        console.error(countUp.error);
      }
    });
  }
};

export default countupInit;
