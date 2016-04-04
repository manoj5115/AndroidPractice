package com.bleedev.mypkb;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.HashMap;
import java.util.Map;

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        Log.d("", "My PKB onKey- " + primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
               // ic.commitText(String.valueOf(code), 1);
                commitText(primaryCode, ic);

        }
    }

    Map<Integer, String> template = new HashMap<Integer, String>(){
        {
            put(49, "Regards,\nManoj Gupta\n+91 9004175857"); // sign
            put(50, "Manoj Gupta"); // name
            put(51, "602, Rameshwaram Apmt, Opp. Keshav Nagar, Vaishali Nagar, NM Road, Dahisar(E), Mumbai-400068."); // address
            put(52, "09004175857"); //phone
            put(53, "manoj5115@gmail.com"); // email
            put(56, "Hi there,\n\nPls see attached CV.\n\nRegards,\n" +
                    "Manoj Gupta\n" +
                    "+91 9004175857"); // CV template

        }
    };
    private void commitText(int primaryCode, InputConnection ic) {
        String txt = template.get(primaryCode);
        if(txt == null){
            char code = (char)primaryCode;
            if(Character.isLetter(code) && caps){
                code = Character.toUpperCase(code);
            }
            ic.commitText(String.valueOf(code), 1);
            return;
        }
        else {
            ic.commitText(txt, 1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
        Log.d("", "My PKB onPress- " + primaryCode);
    }

    @Override
    public void onRelease(int primaryCode) {
        Log.d("", "My PKB onRelease- " + primaryCode);
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }
}