import { getData } from '../../utils';
import fullCalendar from '../fullcalendar';
import events from './events';
import getTemplate from './template';

/*-----------------------------------------------
|   Calendar
-----------------------------------------------*/
const appCalendarInit = () => {
  const Selectors = {
    ACTIVE: '.active',
    ADD_EVENT_FORM: '#addEventForm',
    ADD_EVENT_MODAL: '#addEventModal',
    CALENDAR: '#appCalendar',
    CALENDAR_TITLE: '.calendar-title',
    CALENDAR_DAY: '.calendar-day',
    CALENDAR_DATE: '.calendar-date',
    DATA_CALENDAR_VIEW: '[data-fc-view]',
    DATA_EVENT: 'data-event',
    DATA_VIEW_TITLE: '[data-view-title]',
    EVENT_DETAILS_MODAL: '#eventDetailsModal',
    EVENT_DETAILS_MODAL_CONTENT: '#eventDetailsModal .modal-content',
    EVENT_START_DATE: '#addEventModal [name="startDate"]',
    INPUT_TITLE: '[name="title"]'
  };

  const Events = {
    CLICK: 'click',
    SHOWN_BS_MODAL: 'shown.bs.modal',
    SUBMIT: 'submit'
  };

  const DataKeys = {
    EVENT: 'event',
    FC_VIEW: 'fc-view'
  };

  const eventList = events.reduce(
    (acc, val) =>
      val.schedules ? acc.concat(val.schedules.concat(val)) : acc.concat(val),
    []
  );

  const updateDay = day => {
    const days = [
      'Sunday',
      'Monday',
      'Tuesday',
      'Wednesday',
      'Thursday',
      'Friday',
      'Saturday'
    ];
    return days[day];
  };

  const setCurrentDate = () => {
    const dateObj = new Date();
    const month = dateObj.toLocaleString('en-US', { month: 'short' });
    const date = dateObj.getDate(); // return date number
    const day = dateObj.getDay(); // return week day number
    const year = dateObj.getFullYear();
    const newdate = `${date}  ${month},  ${year}`;
    if (document.querySelector(Selectors.CALENDAR_DAY)) {
      document.querySelector(Selectors.CALENDAR_DAY).textContent =
        updateDay(day);
    }
    if (document.querySelector(Selectors.CALENDAR_DATE)) {
      document.querySelector(Selectors.CALENDAR_DATE).textContent = newdate;
    }
  };
  setCurrentDate();

  const updateTitle = currentData => {
    const { currentViewType } = currentData;
    // week view
    if (currentViewType === 'timeGridWeek') {
      const weekStartsDate = currentData.dateProfile.currentRange.start;
      const startingMonth = weekStartsDate.toLocaleString('en-US', {
        month: 'short'
      });
      const startingDate = weekStartsDate.getDate();
      const weekEndDate = currentData.dateProfile.currentRange.end;

      const endingMonth = weekEndDate.toLocaleString('en-US', {
        month: 'short'
      });
      const endingDate = weekEndDate.getDate();

      document.querySelector(
        Selectors.CALENDAR_TITLE
      ).textContent = `${startingMonth} ${startingDate} - ${endingMonth} ${endingDate}`;
    } else
      document.querySelector(Selectors.CALENDAR_TITLE).textContent =
        currentData.viewTitle;
  };

  const appCalendar = document.querySelector(Selectors.CALENDAR);
  const addEventForm = document.querySelector(Selectors.ADD_EVENT_FORM);
  const addEventModal = document.querySelector(Selectors.ADD_EVENT_MODAL);
  const eventDetailsModal = document.querySelector(
    Selectors.EVENT_DETAILS_MODAL
  );

  if (appCalendar) {
    const calendar = fullCalendar.renderCalendar(appCalendar, {
      headerToolbar: false,
      dayMaxEvents: 3,
      height: 800,
      stickyHeaderDates: false,
      views: {
        week: {
          eventLimit: 3
        }
      },
      eventTimeFormat: {
        hour: 'numeric',
        minute: '2-digit',
        omitZeroMinute: true,
        meridiem: true
      },
      events: eventList,
      eventClick: info => {
        if (info.event.url) {
          window.open(info.event.url, '_blank');
          info.jsEvent.preventDefault();
        } else {
          const template = getTemplate(info.event);
          document.querySelector(
            Selectors.EVENT_DETAILS_MODAL_CONTENT
          ).innerHTML = template;
          const modal = new window.bootstrap.Modal(eventDetailsModal);
          modal.show();
        }
      },
      dateClick(info) {
        const modal = new window.bootstrap.Modal(addEventModal);
        modal.show();
        /* eslint-disable-next-line */
        const flatpickr = document.querySelector(Selectors.EVENT_START_DATE)._flatpickr;
        flatpickr.setDate([info.dateStr]);
      }
    });

    updateTitle(calendar.currentData);

    document.addEventListener('click', e => {
      // handle prev and next button click
      if (
        e.target.hasAttribute(Selectors.DATA_EVENT) ||
        e.target.parentNode.hasAttribute(Selectors.DATA_EVENT)
      ) {
        const el = e.target.hasAttribute(Selectors.DATA_EVENT)
          ? e.target
          : e.target.parentNode;
        const type = getData(el, DataKeys.EVENT);
        switch (type) {
          case 'prev':
            calendar.prev();
            updateTitle(calendar.currentData);
            break;
          case 'next':
            calendar.next();
            updateTitle(calendar.currentData);
            break;
          case 'today':
            calendar.today();
            updateTitle(calendar.currentData);
            break;
          default:
            calendar.today();
            updateTitle(calendar.currentData);
            break;
        }
      }

      // handle fc-view
      if (e.target.hasAttribute('data-fc-view')) {
        const el = e.target;
        calendar.changeView(getData(el, DataKeys.FC_VIEW));
        updateTitle(calendar.currentData);
        document
          .querySelectorAll(Selectors.DATA_CALENDAR_VIEW)
          .forEach(item => {
            if (item === e.target) {
              item.classList.add('active-view');
            } else {
              item.classList.remove('active-view');
            }
          });
      }
    });

    if (addEventForm) {
      addEventForm.addEventListener(Events.SUBMIT, e => {
        e.preventDefault();
        const { title, startDate, endDate, label, description, allDay } =
          e.target;
        calendar.addEvent({
          title: title.value,
          start: startDate.value,
          end: endDate.value ? endDate.value : null,
          allDay: allDay.checked,
          className: `text-${label.value}`,
          description: description.value
        });
        e.target.reset();
        window.bootstrap.Modal.getInstance(addEventModal).hide();
      });
    }

    if (addEventModal) {
      addEventModal.addEventListener(
        Events.SHOWN_BS_MODAL,
        ({ currentTarget }) => {
          currentTarget.querySelector(Selectors.INPUT_TITLE)?.focus();
        }
      );
    }
  }
};

export default appCalendarInit;
