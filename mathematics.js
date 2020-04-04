/* perform arithmetic operations */
const calculate = (n1, operator, n2) => {
  const x = parseFloat(n1)
  const y = parseFloat(n2)
  if (operator === 'add') return x + y
  if (operator === 'subtract') return x - y
  if (operator === 'multiply') return x * y
  if (operator === 'divide') return x / y
  if (operator === 'percentage') return (x / y) * 100
}

/* parse key inputs  */
const getKeyType = key => {
  const { action } = key.dataset
  if (!action) return 'number'
  if (
    action === 'add' ||
    action === 'subtract' ||
    action === 'multiply' ||
    action === 'divide' ||
    action === 'percentage'
  ) return 'operator'

  return action
}

/* generate display screen values */
const generateResult = (key, displayedNum, state) => {
  const keyContent = key.textContent
  const keyType = getKeyType(key)
  const {
    firstValue,
    operator,
    secondValue,
    previousKeyType
  } = state

  if (keyType === 'number') {
    return displayedNum === '0' || previousKeyType === 'operator' || previousKeyType === 'calculate'
      ? keyContent
      : displayedNum + keyContent
  }

  if (keyType === 'decimal') {
    if (!displayedNum.includes('.')) return displayedNum + '.'
    if (previousKeyType === 'operator' || previousKeyType === 'calculate') return '0.'
    return displayedNum
  }

  if (keyType === 'operator') {
    return firstValue && operator && previousKeyType !== 'operator' && previousKeyType !== 'calculate'
      ? calculate(firstValue, operator, displayedNum)
      : displayedNum
  }

  if (keyType === 'calculate') {
    return firstValue
      ? previousKeyType === 'calculate'
        ? calculate(displayedNum, operator, secondValue)
        : calculate(firstValue, operator, displayedNum)
      : displayedNum
  }

  if (keyType === 'clear') return 0
}

/* handle internal state representation */
const updateState = (key, calculator, calculatedValue, displayedNum) => {
  const keyType = getKeyType(key)
  const {
    firstValue,
    operator,
    secondValue,
    previousKeyType
  } = calculator.dataset

  calculator.dataset.previousKeyType = keyType

  if (keyType === 'operator') {
    calculator.dataset.operator = key.dataset.action
    calculator.dataset.firstValue = firstValue &&
      operator &&
      previousKeyType !== 'operator' &&
      previousKeyType !== 'calculate'
      ? calculatedValue
      : displayedNum
  }

  if (keyType === 'calculate') {
    calculator.dataset.secondValue = firstValue && previousKeyType === 'calculate'
      ? secondValue
      : displayedNum
  }

  if (keyType === 'clear') {
    calculator.dataset.firstValue = ''
    calculator.dataset.secondValue = ''
    calculator.dataset.operator = ''
    calculator.dataset.previousKeyType = ''
  }
}

/* handle calculator I/O */
const calculator = document.querySelector('.calculator')
const display = calculator.querySelector('.calculator__display')
const keys = calculator.querySelector('.calculator__keys')

keys.addEventListener('click', e => {
  if (!e.target.matches('button')) return
  const key = e.target
  const displayedNum = display.textContent
  const resultString = generateResult(key, displayedNum, calculator.dataset)

  display.textContent = resultString
  updateState(key, calculator, resultString, displayedNum)
})
