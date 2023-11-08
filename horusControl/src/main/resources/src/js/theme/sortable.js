/* -------------------------------------------------------------------------- */
/*                                 SortableJS                                 */
/* -------------------------------------------------------------------------- */

const sortableInit = () => {
  const { getData } = window.phoenix.utils;

  const sortableEl = document.querySelectorAll('[data-sortable]');

  const defaultOptions = {
    animation: 150,
    group: {
      name: 'shared'
    },
    delay: 100,
    delayOnTouchOnly: true, // useful for mobile touch
    forceFallback: true, // * ignore the HTML5 DnD behaviour
    onStart() {
      document.body.classList.add('sortable-dragging'); // to add cursor grabbing
    },
    onEnd() {
      document.body.classList.remove('sortable-dragging');
    }
  };

  sortableEl.forEach(el => {
    const userOptions = getData(el, 'sortable');
    const options = window._.merge(defaultOptions, userOptions);

    return window.Sortable.create(el, options);
  });
};

export default sortableInit;
