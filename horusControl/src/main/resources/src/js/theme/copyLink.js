/* -------------------------------------------------------------------------- */
/*                                  Copy LinK                                 */
/* -------------------------------------------------------------------------- */

const copyLink = () => {
  const { getData } = window.phoenix.utils;

  const copyButtons = document.querySelectorAll('[data-copy]');

  copyButtons.forEach(button => {
    const tooltip = new window.bootstrap.Tooltip(button);

    button.addEventListener('mouseover', () => tooltip.show());
    button.addEventListener('mouseleave', () => tooltip.hide());

    button.addEventListener('click', () => {
      button.setAttribute('data-bs-original-title', 'Copied');
      tooltip.show();
      const inputID = getData(button, 'copy');
      const input = document.querySelector(inputID);
      input.select();
      navigator.clipboard.writeText(input.value);
      button.setAttribute('data-bs-original-title', 'click to copy');
    });
  });
};

export default copyLink;
