/* eslint-disable no-restricted-syntax */
/* -------------------------------------------------------------------------- */
/*                                 step wizard                                */
/* -------------------------------------------------------------------------- */
const wizardInit = () => {
  const { getData } = window.phoenix.utils;

  const selectors = {
    WIZARDS: '[data-theme-wizard]',
    TOGGLE_BUTTON_EL: '[data-wizard-step]',
    FORMS: '[data-wizard-form]',
    PASSWORD_INPUT: '[data-wizard-password]',
    CONFIRM_PASSWORD_INPUT: '[data-wizard-confirm-password]',
    NEXT_BTN: '[data-wizard-next-btn]',
    PREV_BTN: '[data-wizard-prev-btn]',
    FOOTER: '[data-wizard-footer]',
    KANBAN_STEP: '[data-kanban-step]'
  };
  const ClassName = {
    KANBAN_PROGRESSBAR: 'theme-wizard-progress'
  };

  const events = {
    SUBMIT: 'submit',
    SHOW: 'show.bs.tab',
    SHOWN: 'shown.bs.tab',
    CLICK: 'click'
  };

  const wizards = document.querySelectorAll(selectors.WIZARDS);

  wizards.forEach(wizard => {
    const tabToggleButtonEl = wizard.querySelectorAll(
      selectors.TOGGLE_BUTTON_EL
    );
    const forms = wizard.querySelectorAll(selectors.FORMS);
    const passwordInput = wizard.querySelector(selectors.PASSWORD_INPUT);
    const confirmPasswordInput = wizard.querySelector(
      selectors.CONFIRM_PASSWORD_INPUT
    );
    const nextButton = wizard.querySelector(selectors.NEXT_BTN);
    const prevButton = wizard.querySelector(selectors.PREV_BTN);
    const wizardFooter = wizard.querySelector(selectors.FOOTER);
    const submitEvent = new Event(events.SUBMIT, {
      bubbles: true,
      cancelable: true
    });
    const hasKanbanProgress = wizard.classList.contains(
      ClassName.KANBAN_PROGRESSBAR
    );

    const tabs = Array.from(tabToggleButtonEl).map(item => {
      return window.bootstrap.Tab.getOrCreateInstance(item);
    });
    // console.log({ tabs });

    let count = 0;
    let showEvent = null;

    forms.forEach(form => {
      form.addEventListener(events.SUBMIT, e => {
        e.preventDefault();
        if (form.classList.contains('needs-validation')) {
          if (passwordInput && confirmPasswordInput) {
            if (passwordInput.value !== confirmPasswordInput.value) {
              confirmPasswordInput.setCustomValidity('Invalid field.');
            } else {
              confirmPasswordInput.setCustomValidity('');
            }
          }
          if (!form.checkValidity()) {
            showEvent.preventDefault();
            return false;
          }
        }
        count += 1;
        return null;
      });
    });

    nextButton.addEventListener(events.CLICK, () => {
      if (count + 1 < tabs.length) {
        tabs[count + 1].show();
      }
    });

    prevButton.addEventListener(events.CLICK, () => {
      count -= 1;
      tabs[count].show();
    });

    if (tabToggleButtonEl.length) {
      tabToggleButtonEl.forEach((item, index) => {
        item.addEventListener(events.SHOW, e => {
          const step = getData(item, 'wizard-step');
          showEvent = e;
          if (step > count) {
            forms[count].dispatchEvent(submitEvent);
          }
        });
        item.addEventListener(events.SHOWN, () => {
          count = index;
          // can't go back tab
          if (count === tabToggleButtonEl.length - 1 && !hasKanbanProgress) {
            tabToggleButtonEl.forEach(tab => {
              tab.setAttribute('data-bs-toggle', 'modal');
              tab.setAttribute('data-bs-target', '#error-modal');
            });
          }
          // add done class
          for (let i = 0; i < count; i += 1) {
            tabToggleButtonEl[i].classList.add('done');
            if (i > 0) {
              tabToggleButtonEl[i - 1].classList.add('complete');
            }
          }
          // remove done class
          for (let j = count; j < tabToggleButtonEl.length; j += 1) {
            tabToggleButtonEl[j].classList.remove('done');
            if (j > 0) {
              tabToggleButtonEl[j - 1].classList.remove('complete');
            }
          }

          // card footer remove at last step
          if (count > tabToggleButtonEl.length - 2) {
            wizardFooter.classList.add('d-none');
          } else {
            wizardFooter.classList.remove('d-none');
          }
          // prev-button removing
          if (count > 0 && count !== tabToggleButtonEl.length - 1) {
            prevButton.classList.remove('d-none');
          } else {
            prevButton.classList.add('d-none');
          }
        });
      });
    }
  });
};

export default wizardInit;
