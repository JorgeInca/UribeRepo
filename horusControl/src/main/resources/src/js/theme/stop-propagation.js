/* -------------------------------------------------------------------------- */
/*                                    Toast                                   */
/* -------------------------------------------------------------------------- */

const stopPropagationInit = () => {
  const stopPropagation = document.querySelectorAll('[data-stop-propagation]');
  stopPropagation.forEach(btn => {
    btn.addEventListener('click', e => {
      e.stopPropagation();
    });
  });
};

export default stopPropagationInit;
