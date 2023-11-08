const getTemplate = event => `
<div class="modal-header ps-card border-bottom">
  <div>
    <h4 class="modal-title text-1000 mb-0">${event.title}</h4>
    ${
      event.extendedProps.organizer
        ? `<p class="mb-0 fs--1 mt-1">
        by <a href="#!">${event.extendedProps.organizer}</a>
      </p>`
        : ''
    }
  </div>
  <button type="button" class="btn p-1 fw-bolder" data-bs-dismiss="modal" aria-label="Close">
    <span class='fas fa-times fs-0'></span>
  </button>

</div>

<div class="modal-body px-card pb-card pt-1 fs--1">
  ${
    event.extendedProps.description
      ? `
      <div class="mt-3 border-bottom pb-3">
        <h5 class='mb-0 text-800'>Description</h5>
        <p class="mb-0 mt-2">
          ${event.extendedProps.description.split(' ').slice(0, 30).join(' ')}
        </p>
      </div>
    `
      : ''
  } 
  <div class="mt-4 ${event.extendedProps.location ? 'border-bottom pb-3' : ''}">
    <h5 class='mb-0 text-800'>Date and Time</h5>
    <p class="mb-1 mt-2">
    ${
      window.dayjs &&
      window.dayjs(event.start).format('dddd, MMMM D, YYYY, h:mm A')
    } 
    ${
      event.end
        ? `– ${
            window.dayjs &&
            window
              .dayjs(event.end)
              .subtract(1, 'day')
              .format('dddd, MMMM D, YYYY, h:mm A')
          }`
        : ''
    }
  </p>

  </div>
  ${
    event.extendedProps.location
      ? `
        <div class="mt-4 ">
          <h5 class='mb-0 text-800'>Location</h5>
          <p class="mb-0 mt-2">${event.extendedProps.location}</p>
        </div>
      `
      : ''
  }
  ${
    event.schedules
      ? `
      <div class="mt-3">
        <h5 class='mb-0 text-800'>Schedule</h5>
        <ul class="list-unstyled timeline mt-2 mb-0">
          ${event.schedules
            .map(schedule => `<li>${schedule.title}</li>`)
            .join('')}
        </ul>
      </div>
      `
      : ''
  }
  </div>
</div>

<div class="modal-footer d-flex justify-content-end px-card pt-0 border-top-0">
  <a href="#!" class="btn btn-phoenix-secondary btn-sm">
    <span class="fas fa-pencil-alt fs--2 mr-2"></span> Edit
  </a>
  <button class="btn btn-phoenix-danger btn-sm" data-calendar-event-remove >
    <span class="fa-solid fa-trash fs--1 mr-2" data-fa-transform="shrink-2"></span> Delete
  </button>
  <a href='#!' class="btn btn-primary btn-sm">
    See more details
    <span class="fas fa-angle-right fs--2 ml-1"></span>
  </a>
</div>
`;

export default getTemplate;
