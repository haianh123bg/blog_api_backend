package com.haianh123bg.shopbook.service;

import com.haianh123bg.shopbook.payload.LoginDTO;
import com.haianh123bg.shopbook.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);

}
