# BT
PRODUCT_PROPERTY_OVERRIDES += \
    ro.bt.bdaddr_path=/data/vendor/bluedroid/macbt
    
PRODUCT_PROPERTY_OVERRIDES += \
    persist.bt.enableAptXHD=true \
    persist.service.btui.use_aptx=1 \
    persist.vendor.bt.a2dp_offload_cap=sbc-aptx-aptxtws-aptxhd-aac-ldac \
    persist.vendor.btstack.a2dp_offload_cap=sbc-aptx-aptxtws-aptxhd-aac-ldac

# Dalvik
PRODUCT_PROPERTY_OVERRIDES += \
    dalvik.vm.dex2oat-filter=speed \
    dalvik.vm.image-dex2oat-filter=speed \
    dalvik.vm.heapstartsize=8m \
    dalvik.vm.heapgrowthlimit=384m \
    dalvik.vm.heapsize=512m \
    dalvik.vm.heaptargetutilization=0.75 \
    dalvik.vm.heapminfree=512k \
    dalvik.vm.heapmaxfree=8m

PRODUCT_PROPERTY_OVERRIDES += \
    debug.hwui.profile=true \
    ro.opengles.version=196610

# DRM
PRODUCT_PROPERTY_OVERRIDES += \
    ro.config.widevine_level1=true \
    drm.service.enabled=true

PRODUCT_PROPERTY_OVERRIDES += \
    ro.media.radar=0 \
    ro.config.enable_iaware=true
    
# Launcher
PRODUCT_PROPERTY_OVERRIDES += \
   lockscreen.rot_override=true

# local
PRODUCT_PROPERTY_OVERRIDES += \
    net.tethering.noprovisioning=true \
    ro.config.hw_lte_support=true \
    gsm.fastdormancy.mode=3

# FRP
PRODUCT_PROPERTY_OVERRIDES += \
    ro.frp.pst=/dev/block/platform/hi_mci.0/by-name/frp

# Gralloc
PRODUCT_PROPERTY_OVERRIDES += \
    ro.config.mmu_en=1
    
# Media
PRODUCT_PROPERTY_OVERRIDES += \
    persist.media.offload.enable=true \
    media.stagefright.thumbnail.prefer_hw_codecs=true \
    media.stagefright.enable-player=true \
    media.stagefright.enable-http=true \
    media.stagefright.enable-aac=true \
    media.stagefright.enable-qcp=true \
    media.stagefright.enable-scan=true \
    debug.stagefright.ccodec=0 \
    debug.stagefright.omx_default_rank.sw-audio=1 \
    debug.stagefright.omx_default_rank=0

# Misc
PRODUCT_PROPERTY_OVERRIDES += \
    persist.sys.huawei.debug.on=1 \
    qemu.hw.mainkeys=0
    
# Netflix
PRODUCT_PROPERTY_OVERRIDES += \
    ro.netflix.bsp_rev=Q660-13149-1

# Performance
PRODUCT_PROPERTY_OVERRIDES += \
    ro.vendor.qti.sys.fw.bg_apps_limit=60 \
    ro.am.reschedule_service=true \
    persist.sys.smart_power=2

# RIL
PRODUCT_PROPERTY_OVERRIDES += \
    ro.config.ipv4.mtu=1400 \
    audioril.lib=libhuawei-audio-ril.so \
    persist.radio.apm_sim_not_pwdn=1 \
    ro.pwroff_card_if_tray_out=true
    
# Sdcardfs
PRODUCT_PROPERTY_OVERRIDES += \
    ro.statsd.enable=false \
    ro.sys.sdcardfs=1 \
    persist.sys.adoptable_fbe=true

# Sensors
PRODUCT_PROPERTY_OVERRIDES += \
    ro.config.hw_sensorhub=true

# wifi
PRODUCT_PROPERTY_OVERRIDES += \
    wifi.interface=wlan0 \
    wifi.direct.interface=p2p0

# Q
PRODUCT_PROPERTY_OVERRIDES += \
    ro.surface_flinger.protected_contents=true \
    ro.surface_flinger.max_frame_buffer_acquired_buffers=3

PRODUCT_PROPERTY_OVERRIDES += \
    ro.vr_display.service=true \
    persist.irqbalance.enable=true

# Touch
PRODUCT_PROPERTY_OVERRIDES += \
   persist.sys.fingersense=1
   
# USB
PRODUCT_PROPERTY_OVERRIDES += \
    persist.sys.usb.config.extra=none
