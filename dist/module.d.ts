export declare type AndroidKeyboardEventListener = (height: number) => void;
export declare enum SoftInputMode {
    SOFT_INPUT_ADJUST_NOTHING = "SOFT_INPUT_ADJUST_NOTHING",
    SOFT_INPUT_ADJUST_PAN = "SOFT_INPUT_ADJUST_PAN",
    SOFT_INPUT_ADJUST_RESIZE = "SOFT_INPUT_ADJUST_RESIZE",
    SOFT_INPUT_ADJUST_UNSPECIFIED = "SOFT_INPUT_ADJUST_UNSPECIFIED",
    SOFT_INPUT_IS_FORWARD_NAVIGATION = "SOFT_INPUT_IS_FORWARD_NAVIGATION",
    SOFT_INPUT_MASK_ADJUST = "SOFT_INPUT_MASK_ADJUST",
    SOFT_INPUT_MASK_STATE = "SOFT_INPUT_MASK_STATE",
    SOFT_INPUT_MODE_CHANGED = "SOFT_INPUT_MODE_CHANGED",
    SOFT_INPUT_STATE_ALWAYS_HIDDEN = "SOFT_INPUT_STATE_ALWAYS_HIDDEN",
    SOFT_INPUT_STATE_ALWAYS_VISIBLE = "SOFT_INPUT_STATE_ALWAYS_VISIBLE",
    SOFT_INPUT_STATE_HIDDEN = "SOFT_INPUT_STATE_HIDDEN",
    SOFT_INPUT_STATE_UNCHANGED = "SOFT_INPUT_STATE_UNCHANGED",
    SOFT_INPUT_STATE_UNSPECIFIED = "SOFT_INPUT_STATE_UNSPECIFIED",
    SOFT_INPUT_STATE_VISIBLE = "SOFT_INPUT_STATE_VISIBLE"
}
export declare class AndroidKeyboardStatic {
    private initialized;
    private listeners;
    constructor();
    setWindowSoftInputMode(mode: SoftInputMode): void;
    addListener(listener: AndroidKeyboardEventListener): void;
    removeListener(listener: AndroidKeyboardEventListener): void;
    private ensureInitialized;
}
export declare const AndroidKeyboard: AndroidKeyboardStatic;
