package org.example.coinbase.dto

data class ListAddressesDto(
    //pagination
    val data: List<Address>
) {
    data class Address (
        val id: String,
        val address: String,
        val address_info: AddressInfo,
        val name: String,
        val created_at: String,
        val updated_at: String,
        val network: String,
        val uri_scheme: String,
        val resource: String,
        val resource_path: String,
        val warnings: List<Any?>,
        val qr_code_image_url: String,
        val address_label: String,
        val default_receive: Boolean,
        val deposit_uri: String,
        val callback_url: String,
        val share_address_copy: ShareAddressCopy,
        val receive_subtitle: String,
        val inline_warning: InlineWarning
    ) {
        data class AddressInfo (
            val address: String,
            val destination_tag: String
        )

        data class InlineWarning (
            val text: String,
            val tooltip: Tooltip
        )

        data class Tooltip (
            val title: String,
            val subtitle: String
        )

        data class ShareAddressCopy (
            val line1: String,
            val line2: String
        )
    }
}
