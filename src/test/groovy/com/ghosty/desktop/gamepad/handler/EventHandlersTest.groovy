/*
 * Gamepad Desktop Control
 * Copyright (c) 2015. Ievgen Starodiedov
 */

package com.ghosty.desktop.gamepad.handler

import com.ghosty.desktop.gamepad.model.MouseMove
import com.ghosty.desktop.gamepad.utils.MouseController
import net.java.games.input.Component
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import static com.ghosty.desktop.gamepad.handler.EventHandlers.*
import static com.ghosty.desktop.gamepad.model.Direction.*
import static com.ghosty.desktop.gamepad.utils.EventHandlerUtils.MOUSE_CONTROLLER
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EventHandlersTest {

    @Mock
    private Component component

    @Mock
    private MouseController mouseController

    @Captor
    private ArgumentCaptor<MouseMove> captor

    @BeforeClass
    public static void before() {
        MOUSE_CONTROLLER = mock MouseController.class
    }

    @Test
    public void "1 test left click"() {
        when component.getPollData() thenReturn 1F
        LEFT_MOUSE_CLICK_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseLeftPress()

        when component.getPollData() thenReturn 0F
        LEFT_MOUSE_CLICK_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseLeftRelease()
    }

    @Test
    public void "2 test right click"() {
        when component.getPollData() thenReturn 1F
        RIGHT_MOUSE_CLICK_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseRightPress()

        when component.getPollData() thenReturn 0F
        RIGHT_MOUSE_CLICK_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseRightRelease()
    }

    @Test
    public void "3 test static analog components"() {
        when component.getPollData() thenReturn 0F
        CURSOR_LEFT_RIGHT_MOVE_EMULATOR.handle component
        verifyZeroInteractions MOUSE_CONTROLLER

        CURSOR_UP_DOWN_MOVE_EMULATOR.handle component
        verifyZeroInteractions MOUSE_CONTROLLER

        WHEEL_SCROLL_EMULATOR.handle component
        verifyZeroInteractions MOUSE_CONTROLLER
    }

    @Test
    public void "4 test right left cursor moves"() {
        // To reset all interactions
        MOUSE_CONTROLLER = mock MouseController.class
        when component.getPollData() thenReturn 0.8F
        CURSOR_LEFT_RIGHT_MOVE_EMULATOR.handle component

        verify MOUSE_CONTROLLER moveMouse captor.capture()
        assert captor.value.direction == RIGHT
        assert captor.value.speed == 8

        // To reset all interactions
        MOUSE_CONTROLLER = mock MouseController.class
        when component.getPollData() thenReturn -0.3F
        CURSOR_LEFT_RIGHT_MOVE_EMULATOR.handle component

        verify MOUSE_CONTROLLER moveMouse captor.capture()
        assert captor.value.direction == LEFT
        assert captor.value.speed == 3
    }

    @Test
    public void "5 test up down cursor moves"() {
        // To reset all interactions
        MOUSE_CONTROLLER = mock MouseController.class
        when component.getPollData() thenReturn 1F
        CURSOR_UP_DOWN_MOVE_EMULATOR.handle component

        verify MOUSE_CONTROLLER moveMouse captor.capture()
        assert captor.value.direction == UP
        assert captor.value.speed == 10

        // To reset all interactions
        MOUSE_CONTROLLER = mock MouseController.class
        when component.getPollData() thenReturn -1F
        CURSOR_UP_DOWN_MOVE_EMULATOR.handle component

        verify MOUSE_CONTROLLER moveMouse captor.capture()
        assert captor.value.direction == DOWN
        assert captor.value.speed == 10
    }

    @Test
    public void "6 test wheel scroll"() {
        when component.getPollData() thenReturn 0.5F
        WHEEL_SCROLL_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseScrollUp()

        when component.getPollData() thenReturn -0.6F
        WHEEL_SCROLL_EMULATOR.handle component
        verify MOUSE_CONTROLLER mouseScrollDown()
    }
}
