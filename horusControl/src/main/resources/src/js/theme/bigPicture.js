/* -------------------------------------------------------------------------- */
/*                                 bigPicture                                 */
/* -------------------------------------------------------------------------- */
const bigPictureInit = () => {
  const { getData } = window.phoenix.utils;
  if (window.BigPicture) {
    const bpItems = document.querySelectorAll('[data-bigpicture]');
    bpItems.forEach(bpItem => {
      const userOptions = getData(bpItem, 'bigpicture');
      const defaultOptions = {
        el: bpItem,
        noLoader: true,
        allowfullscreen: true
      };
      const options = window._.merge(defaultOptions, userOptions);

      bpItem.addEventListener('click', () => {
        window.BigPicture(options);
      });
    });
  }
};

export default bigPictureInit;
