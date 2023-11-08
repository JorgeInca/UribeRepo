/* -------------------------------------------------------------------------- */
/*                                   Popover                                  */
/* -------------------------------------------------------------------------- */

const picmoInit = () => {
  const { getData } = window.phoenix.utils;

  const picmoBtns = document.querySelectorAll('[data-picmo]');

  if (picmoBtns) {
    Array.from(picmoBtns).forEach(btn => {
      const options = getData(btn, 'picmo');

      const picker = window.picmoPopup.createPopup(
        {},
        {
          referenceElement: btn,
          triggerElement: btn,
          position: 'bottom-start',
          showCloseButton: false
        }
      );
      btn.addEventListener('click', () => {
        picker.toggle();
      });

      const input = document.querySelector(options.inputTarget);

      picker.addEventListener('emoji:select', selection => {
        if (input) {
          input.innerHTML += selection.emoji;
        }
      });
    });
  }
};

export default picmoInit;
