/* -------------------------------------------------------------------------- */
/*                                    TODO Offacanvas                                   */
/* -------------------------------------------------------------------------- */

const todoOffcanvasInit = () => {
  const { getData } = window.phoenix.utils;

  const stopPropagationElements = document.querySelectorAll(
    '[data-event-propagation-prevent]'
  );

  if (stopPropagationElements) {
    stopPropagationElements.forEach(el => {
      el.addEventListener('click', e => {
        e.stopPropagation();
      });
    });
  }

  const todoList = document.querySelector('.todo-list');

  if (todoList) {
    const offcanvasToggles = todoList.querySelectorAll(
      '[data-todo-offcanvas-toogle]'
    );

    offcanvasToggles.forEach(toggle => {
      const target = getData(toggle, 'todo-offcanvas-target');
      const offcanvasEl = todoList.querySelector(`#${target}`);
      const todoOffcanvas = new window.bootstrap.Offcanvas(offcanvasEl, {
        backdrop: true
      });
      toggle.addEventListener('click', () => {
        todoOffcanvas.show();
      });
    });
  }
};

export default todoOffcanvasInit;
