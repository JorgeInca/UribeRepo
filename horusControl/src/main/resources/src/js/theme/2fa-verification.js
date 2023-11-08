const towFAVerificarionInit = () => {
  const verificationForm = document.querySelector('[data-2FA-varification]');
  const inputFields = document.querySelectorAll(
    '[data-2FA-varification] input[type=number]'
  );

  if (verificationForm) {
    window.addEventListener('load', () => inputFields[0].focus());
    // check if the value is not a number
    verificationForm.addEventListener('keypress', e => {
      if (e.target.matches('input[type=number]')) {
        if (/\D/.test(e.key) || !!e.target.value) {
          e.preventDefault();
        }
      }
    });

    // after entering a value get focus on the next input field and remove the disabled attribute
    const inputs = [...inputFields];
    verificationForm.addEventListener('input', e => {
      if (e.target.matches('input[type=number]')) {
        const index = inputs.indexOf(e.target);
        const nextInput = inputs[index + 1];
        if (
          nextInput &&
          e.target.value !== '' &&
          nextInput.hasAttribute('disabled')
        ) {
          nextInput.removeAttribute('disabled');
          nextInput.focus();
        }
      }
    });

    // backspace functionality
    verificationForm.addEventListener('keydown', e => {
      if (e.target.matches('input[type=number]') && e.keyCode === 8) {
        const index = inputs.indexOf(e.target);
        const prevInput = inputs[index - 1];
        if (prevInput) {
          prevInput.focus();
          e.target.value = '';
          e.target.setAttribute('disabled', true);
        }
      }
    });

    // return merged code
    verificationForm.addEventListener('submit', () => {
      const code = inputs.map(input => input.value).join('');
      return code;
    });
  }
};

export default towFAVerificarionInit;
