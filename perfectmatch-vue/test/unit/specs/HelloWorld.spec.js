import HelloWorld from '@/components/HelloWorld'
import { mount } from '@vue/test-utils'

describe('HelloWorld.vue', () => {
  it('should render correct contents', () => {

    const wrapper = mount(HelloWorld)
    console.log(wrapper.html())
    const vm = wrapper.vm
    expect(vm.$el.querySelector('.hello button').textContent)
      .toEqual('Hello Button')
  })
})
