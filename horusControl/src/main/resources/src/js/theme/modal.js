/* -------------------------------------------------------------------------- */
/*                               Modal                               */
/* -------------------------------------------------------------------------- */

const modalInit = () => {
  const $modals = document.querySelectorAll('[data-phoenix-modal]');

  if ($modals) {
    $modals.forEach(modal => {
      modal.addEventListener('shown.bs.modal', () => {
        const $autofocusEls = modal.querySelectorAll('[autofocus=autofocus]');
        $autofocusEls.forEach(el => {
          el.focus();
        });
      });
    });
  }
};
export default modalInit;
