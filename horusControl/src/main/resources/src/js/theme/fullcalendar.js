/* -------------------------------------------------------------------------- */
/*                                   Calendar                                 */

/* -------------------------------------------------------------------------- */
const renderCalendar = (el, option) => {
  const { merge } = window._;

  const options = merge(
    {
      initialView: 'dayGridMonth',
      editable: true,
      direction: document.querySelector('html').getAttribute('dir'),
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      buttonText: {
        month: 'Month',
        week: 'Week',
        day: 'Day'
      }
    },
    option
  );
  const calendar = new window.FullCalendar.Calendar(el, options);
  calendar.render();
  document
    .querySelector('.navbar-vertical-toggle')
    ?.addEventListener('navbar.vertical.toggle', () => calendar.updateSize());
  return calendar;
};

export const fullCalendarInit = () => {
  const { getData } = window.phoenix.utils;

  const calendars = document.querySelectorAll('[data-calendar]');
  calendars.forEach(item => {
    const options = getData(item, 'calendar');
    renderCalendar(item, options);
  });
};

const fullCalendar = {
  renderCalendar,
  fullCalendarInit
};
export default fullCalendar;
