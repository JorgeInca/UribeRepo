/* -------------------------------------------------------------------------- */
/*                                    Toast                                   */
/* -------------------------------------------------------------------------- */

const simplebarInit = () => {
  const scrollEl = Array.from(document.querySelectorAll('.scrollbar-overlay'));

  scrollEl.forEach(el => {
    return new window.SimpleBar(el);
  });
};

export default simplebarInit;
