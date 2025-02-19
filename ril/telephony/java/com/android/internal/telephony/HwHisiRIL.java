/*
 * Copyright (c) 2012-2013, The Linux Foundation. All rights reserved.
 * Not a Contribution.
 *
 * Copyright (C) 2006 The Android Open Source Project
 * Copyright (C) 2016 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony;

import static com.android.internal.telephony.RILConstants.*;

import android.content.Context;
import android.os.Parcel;

import android.telephony.Rlog;
import android.telephony.SignalStrength;

/**
 * RIL customization for alice devices
 *
 * {@hide}
 */
public class HwHisiRIL extends RIL {

    private static final String RILJ_LOG_TAG = "RILJ-HwHisiRil";

    private final boolean DBG = false;

    public HwHisiRIL(Context context, int networkMode, int cdmaSubscription, Integer instanceId) {
        super(context, networkMode, cdmaSubscription, instanceId);
    }

    @Override
    protected Object
    responseFailCause(Parcel p) {
        int numInts;
        int response[];

        numInts = p.readInt();
        response = new int[numInts];
        for (int i = 0 ; i < numInts ; i++) {
            response[i] = p.readInt();
        }
        LastCallFailCause failCause = new LastCallFailCause();
        failCause.causeCode = response[0];
        if (p.dataAvail() > 0) {
          failCause.vendorCause = p.readString();
        }
        return failCause;
    }

    @Override
    protected void
    send(RILRequest rr) {
        if (rr.mRequest >= 132) {
            Rlog.i(RILJ_LOG_TAG, ": Unsupported request " + rr.mRequest);
            rr.onError(REQUEST_NOT_SUPPORTED, null);
            rr.release();
        } else {
            super.send(rr);
        }
    }

    @Override
    protected Object
    responseSignalStrength(Parcel p) {

        Rlog.e(RILJ_LOG_TAG, "responseSignalStrength called");

        int[] response = new int[14];
        for (int i = 0; i < 14; i++) {
            response[i] = p.readInt();
        }

        int gsmSignalStrength = response[0];
        int gsmBitErrorRate = response[1];
        int wcdmaRscp = response[2];
        int wcdmaEcio = response[3];
        int cdmaDbm = response[4];
        int cdmaEcio = response[5];
        int evdoDbm = response[6];
        int evdoEcio = response[7];
        int evdoSnr = response[8];
        int lteSignalStrength = response[9];
        int lteRsrp = response[10];
        int lteRsrq = response[11];
        int lteRssnr = response[12];
        int lteCqi = response[13];


        Rlog.d(RILJ_LOG_TAG, "---------- Lte Values ----------");
        Rlog.d(RILJ_LOG_TAG, "lteSignalStrength:" + lteSignalStrength);
        Rlog.d(RILJ_LOG_TAG, "lteRsrp:" + lteRsrp);
        Rlog.d(RILJ_LOG_TAG, "lteRsrq:" + lteRsrq);
        Rlog.d(RILJ_LOG_TAG, "lteRssnr:" + lteRssnr);
        Rlog.d(RILJ_LOG_TAG, "lteCqi:" + lteCqi);
        Rlog.d(RILJ_LOG_TAG, "-------------------------");

        Rlog.d(RILJ_LOG_TAG, "---------- Other Values ----------");
        Rlog.d(RILJ_LOG_TAG, "cdmaDbm:" + cdmaDbm);
        Rlog.d(RILJ_LOG_TAG, "cdmaEcio:" + cdmaEcio);
        Rlog.d(RILJ_LOG_TAG, "gsmSignalStrength:" + gsmSignalStrength);
        Rlog.d(RILJ_LOG_TAG, "evdoDbm:" + evdoDbm);
        Rlog.d(RILJ_LOG_TAG, "-------------------------");

        /* LTE */
        if (lteRsrp >= -97) {
            lteSignalStrength = 63;
            lteRssnr = 130;
            lteRsrp = -98;
        } else if (lteRsrp >= -105) {
            lteSignalStrength = 10;
            lteRssnr = 45;
            lteRsrp = -108;
        } else if (lteRsrp >= -113) {
            lteSignalStrength = 5;
            lteRssnr = 10;
            lteRsrp = -118;
        } else if (lteRsrp >= -125) {
            lteSignalStrength = 3;
            lteRssnr = -30;
            lteRsrp = -128;
        } else if (lteRsrp >= -44) {
            lteSignalStrength = 64;
            lteRssnr = -200;
            lteRsrp = -140;
        }

        /* EvDO */
        if (evdoDbm >= -89) {
            evdoDbm = -65;
            evdoSnr = 7;
        } else if (evdoDbm >= -99) {
            evdoDbm = -75;
            evdoSnr = 5;
        } else if (evdoDbm >= -106) {
            evdoDbm = -90;
            evdoSnr = 3;
        } else if (evdoDbm >= -112) {
            evdoDbm = -105;
            evdoSnr = 1;
        } else {
            evdoDbm = -999;
            evdoSnr = -999;
        }

        /* CDMA */
        if (cdmaDbm >= -89) {
            cdmaDbm = -75;
            cdmaEcio = -90;
        } else if (cdmaDbm >= -99) {
            cdmaDbm = -85;
            cdmaEcio = -110;
        } else if (cdmaDbm >= -106) {
            cdmaDbm = -95;
            cdmaEcio = -130;
        } else if (cdmaDbm >= -112) {
            cdmaDbm = -100;
            cdmaEcio = -150;
        } else {
            cdmaDbm = -998;
            cdmaEcio = -998;
        }

        /* GSM */
        if (gsmSignalStrength >= -89) {
            gsmSignalStrength = 12;
        } else if (gsmSignalStrength >= -97) {
            gsmSignalStrength = 8;
        } else if (gsmSignalStrength >= -103) {
            gsmSignalStrength = 5;
        } else {
            gsmSignalStrength = 0;
        }

        if (gsmSignalStrength != -1) {
            if (DBG) {
                Rlog.i(RILJ_LOG_TAG, ": original gsmSignalStrength: " + gsmSignalStrength);
            }
            gsmSignalStrength = -(gsmSignalStrength - 113) / 2;
	}

	SignalStrength sst = new SignalStrength(gsmSignalStrength, gsmBitErrorRate, cdmaDbm, cdmaEcio, evdoDbm, evdoEcio, evdoSnr,
            lteSignalStrength, lteRsrp, lteRsrq, lteRssnr, lteCqi, wcdmaRscp, true);

        if (DBG) {
            Rlog.i(RILJ_LOG_TAG, ": " + sst.toString() + " " + gsmSignalStrength);
		}

    return sst;
    }
}
