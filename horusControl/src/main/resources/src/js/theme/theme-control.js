import DomNode from './node';

/* -------------------------------------------------------------------------- */
/*                                Theme Control                               */
/* -------------------------------------------------------------------------- */
/* eslint-disable no-param-reassign */
/* eslint-disable */
const { config } = window.config;

const initialDomSetup = element => {
  const { getData, getItemFromStore } = window.phoenix.utils;
  if (!element) return;

  element.querySelectorAll('[data-theme-control]').forEach(el => {
    const inputDataAttributeValue = getData(el, 'theme-control');
    const localStorageValue = getItemFromStore(inputDataAttributeValue);

    // diable horizontal navbar shape for dual nav
    if (
      inputDataAttributeValue === 'phoenixNavbarTopShape' &&
      getItemFromStore('phoenixNavbarPosition') === 'dual-nav')
        {
      el.setAttribute('disabled', true);
    }

    // diable navbar vertical style for horizontal & dual navbar
    const currentNavbarPosition = getItemFromStore('phoenixNavbarPosition');
    const isHorizontalOrDualNav = (currentNavbarPosition === "horizontal") || (currentNavbarPosition ==='dual-nav')
    if (
      inputDataAttributeValue === 'phoenixNavbarVerticalStyle' &&
      isHorizontalOrDualNav)
      {
        el.setAttribute('disabled', true);
      }

    if (el.type === 'checkbox') {
      if (inputDataAttributeValue === 'phoenixTheme') {
        localStorageValue === 'dark' && el.setAttribute('checked', true);
      } else {
        localStorageValue && el.setAttribute('checked', true);
      }
    } else if (
      el.type === 'radio' &&
      inputDataAttributeValue === 'phoenixNavbarVerticalStyle'
    ) {
      localStorageValue === 'darker' &&
        el.value === 'darker' &&
        el.setAttribute('checked', true);
      localStorageValue === 'default' &&
        el.value === 'default' &&
        el.setAttribute('checked', true);
    } else if (
      el.type === 'radio' &&
      inputDataAttributeValue === 'phoenixNavbarTopShape'
    ) {
      localStorageValue === 'slim' &&
        el.value === 'slim' &&
        el.setAttribute('checked', true);
      localStorageValue === 'default' &&
        el.value === 'default' &&
        el.setAttribute('checked', true);
    } else if (
      el.type === 'radio' &&
      inputDataAttributeValue === 'phoenixNavbarTopStyle'
    ) {
      localStorageValue === 'darker' &&
        el.value === 'darker' &&
        el.setAttribute('checked', true);
      localStorageValue === 'default' &&
        el.value === 'default' &&
        el.setAttribute('checked', true);
    } else {
      const isChecked = localStorageValue === el.value;
      isChecked && el.setAttribute('checked', true);
    }
  });
};

const changeTheme = element => {
  const { getData, getItemFromStore } = window.phoenix.utils;

  element
    .querySelectorAll('[data-theme-control = "phoenixTheme"]')
    .forEach(el => {
      const inputDataAttributeValue = getData(el, 'theme-control');
      const localStorageValue = getItemFromStore(inputDataAttributeValue);

      if (el.type === 'checkbox') {
        localStorageValue === 'dark'
          ? (el.checked = true)
          : (el.checked = false);
      } else {
        localStorageValue === el.value
          ? (el.checked = true)
          : (el.checked = false);
      }
    });
};

const themeControl = () => {
  const { getData, getItemFromStore } = window.phoenix.utils;

  const handlePageUrl = el => {
    const pageUrl = getData(el, 'page-url');
    if (pageUrl) {
      window.location.replace(pageUrl);
    } else {
      window.location.reload();
    }
  };

  const themeController = new DomNode(document.body);

  const navbarVertical = document.querySelector('.navbar-vertical');
  const navbarTop = document.querySelector('.navbar-top');
  const supportChat = document.querySelector('.support-chat-container');
  initialDomSetup(themeController.node);

  themeController.on('click', e => {
    const target = new DomNode(e.target);

    if (target.data('theme-control')) {
      const control = target.data('theme-control');

      let value = e.target[e.target.type === 'radio' ? 'value' : 'checked'];

      if (control === 'phoenixTheme') {
        typeof value === 'boolean' && (value = value ? 'dark' : 'light');
      }

      // config.hasOwnProperty(control) && setItemToStore(control, value);
      config.hasOwnProperty(control) &&
        window.config.set({
          [control]: value
        });

      window.history.replaceState(null, null, window.location.pathname);
      switch (control) {
        case 'phoenixTheme': {
          document.documentElement.classList[
            value === 'dark' ? 'add' : 'remove'
          ]('dark');
          const clickControl = new CustomEvent('clickControl', {
            detail: { control, value }
          });
          e.currentTarget.dispatchEvent(clickControl);
          changeTheme(themeController.node);
          break;
        }
        case 'phoenixNavbarVerticalStyle': {
          navbarVertical.classList.remove('navbar-darker');          
          if (value !== 'default') {
            navbarVertical.classList.add(`navbar-${value}`);
          }
          break;
        }
        case 'phoenixNavbarTopStyle': {
          navbarTop.classList.remove('navbar-darker');
          if (value !== 'transparent') {
            navbarTop.classList.add(`navbar-${value}`);
          }
          break;
        }
        case 'phoenixNavbarTopShape':
          {
            if(getItemFromStore('phoenixNavbarPosition') === 'dual-nav'){
              el.setAttribute('disabled', true);
            }
            else 
              handlePageUrl(target.node);
          }
          break;
        case 'phoenixNavbarPosition':
          {
            handlePageUrl(target.node);
          }

          break;
        case 'phoenixIsRTL':
          {
            // localStorage.setItem('phoenixIsRTL', target.node.checked);
            window.config.set({
              phoenixIsRTL: target.node.checked
            });
            window.location.reload();
          }
          break;

        case 'phoenixSupportChat': {
          supportChat?.classList.remove('show');
          if (value) {
            supportChat?.classList.add('show');
          }
          break;
        }

        case 'reset': {
          window.config.reset();
          window.location.reload();
          break;
        }

        default: {
          window.location.reload();
        }
      }
    }
  });
};

export default themeControl;
