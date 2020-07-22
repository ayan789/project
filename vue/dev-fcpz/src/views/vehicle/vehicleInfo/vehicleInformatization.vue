<!--
 * @Author: King
 * @Page: 车辆定型
 * @Date: 2020-04-02 20:45:50
 * @Last Modified by: King
 * @Last Modified time: 2020-07-06 14:40:34
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleInformatization.vue
-->
<template>
  <div class="vehicle-informatization">
    <steps-nav-bar
      active="车辆信息"
      type="vehicle"
      left
      @back="back()"
      :right="{
        icon: 'wap-home-o',
        click: () => goHome()
      }"
    />
    <div class="vehicle-title">录入车辆信息</div>
    <div class="content" ref="content">
      <div class="group-content">
        <van-cell class="item">
          <span slot="title" class="item-title">是否代理</span>
          <div class="item-right">
            <whether-select
              :disabled="isAgentUser"
              v-model="projectChannel.isProxy"
              :options="[
                { text: '代理', value: '1' },
                { disabled: isAgentUser, text: '非代理', value: '0' }
              ]"
              @change="changeAgentChannel"
            />
          </div>
        </van-cell>
        <van-cell
          class="item"
          v-if="projectChannel.isProxy === '0'"
          required
          @click="toVehicleChannel"
        >
          <span slot="title" class="item-title">直销/直团渠道</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !projectChannel.businessNatureCode
              }"
              >{{ projectChannel.businessNatureName }}</span
            >
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-cell
          class="item"
          v-if="projectChannel.isProxy === '1'"
          required
          is-link
          @click="toVehicleChannel"
        >
          <span slot="title" class="item-title">代理人</span>
          <span class="item-right">{{ projectChannel.agentName }}</span>
        </van-cell>
      </div>
      <van-cell class="group">
        <div slot="title" class="group-title">
          <img
            width="16px"
            height="16px"
            src="../../../assets/vehicle/vehicleInfo/vehicle.png"
          />
          车辆信息
        </div>
        <div class="group-right" @click.stop="OCR('drivingLicense')">
          <span class="group-right-text">行驶证识别</span>
          <van-icon name="photograph" size="16" color="#3B8AFE" />
        </div>
      </van-cell>
      <div class="group-content">
        <div class="item-container">
          <img
            class="item-ocr-image"
            v-if="control.licenseNoImage"
            :src="control.licenseNoImage"
          />
          <van-cell class="item" required>
            <span slot="title" class="item-title">车牌号</span>
            <div class="item-right">
              <div class="license-no-input-container">
                <input
                  :readonly="!noLicenseFlag"
                  ref="licenseNo"
                  class="license-no-input"
                  v-model="licenseNo"
                  placeholder="请输入车牌号"
                  :maxlength="noLicenseFlag ? 20 : 11"
                  @click="licenseNoClick"
                  @focus="licenseNoFocus"
                  @blur="licenseNoBlur"
                />
              </div>
              <van-checkbox
                class="check-box"
                icon-size="18"
                v-model="noLicenseFlag"
              >
                <span class="check-box-title">未上牌</span>
              </van-checkbox>
              <img
                class="search-image"
                src="../../../assets/vehicle/vehicleInfo/search.png"
                @click="carInfoByLicenseNo"
              />
            </div>
          </van-cell>
        </div>
        <!-- 广东 四川 -->
        <!-- <van-cell
          v-show="areaCode == '4401' || areaCode == '5101'"
          class="item"
          is-link
          :required="car.noLicenseFlag != 1"
          @click="
            car.noLicenseFlag != 1 || areaCode == '4403'
              ? setPicker('licenseType')
              : $dialog.alert({ message: '未上牌时不允许录入号牌类型' })
          "
        >
          <span slot="title" class="item-title">号牌类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.licenseType }">
              {{ $utils.findText(licenseTypes, car.licenseType) }}
            </span>
          </div>
        </van-cell> -->
        <div class="item-container">
          <img
            class="item-ocr-image"
            v-if="control.frameNoImage"
            :src="control.frameNoImage"
          />
          <van-cell class="item" required>
            <span slot="title" class="item-title">车架号</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="frameNo"
                maxlength="17"
                placeholder="请输入车架号"
                @focus="e => (e.target.placeholder = '')"
                @blur="frameNoBlur"
              />
              <img
                class="search-image"
                src="../../../assets/vehicle/vehicleInfo/search.png"
                @click="carInfoByFrameNo"
              />
            </div>
          </van-cell>
        </div>
        <div class="item-container">
          <img
            class="item-ocr-image"
            v-if="control.enrollDateImage"
            :src="control.enrollDateImage"
          />
          <van-cell
            class="item"
            required
            is-link
            @click="changeDatePickerType('enrollDate')"
          >
            <span slot="title" class="item-title">初登日期</span>
            <div class="item-right">
              <span
                :class="{
                  'item-right-text': true,
                  'item-placeholder': !car.enrollDate
                }"
                >{{ car.enrollDate | date }}</span
              >
            </div>
          </van-cell>
        </div>
        <div class="item-container">
          <img
            class="item-ocr-image"
            v-if="control.engineNoImage"
            :src="control.engineNoImage"
          />
          <van-cell class="item" required>
            <span slot="title" class="item-title">发动机号</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="engineNo"
                placeholder="请输入"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
            </div>
          </van-cell>
        </div>
        <div class="item-container">
          <img
            class="item-ocr-image"
            v-if="control.vehicleBrandImage"
            :src="control.vehicleBrandImage"
          />
          <van-cell class="item" required>
            <span slot="title" class="item-title">厂牌型号</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="vehicleBrand"
                placeholder="请输入"
                maxlength="20"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入')"
              />
              <!-- <img
              class="search-image"
              src="../../../assets/vehicle/vehicleInfo/search.png"
              @click="selectCarModel"
              />-->
              <div
                class="item-right-button item-right-button-text"
                @click="selectCarModel"
              >
                车型查询
              </div>
            </div>
          </van-cell>
        </div>
        <van-cell class="item" required>
          <span slot="title" class="item-title">核定载客数</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="seatCount"
              placeholder="请输入"
              maxlength="3"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入')"
            />
          </div>
        </van-cell>
        <van-cell class="item" required v-if="vehicleType !== '3'">
          <span slot="title" class="item-title">车型分类代码</span>
          <div class="item-right">
            <span>{{ car.vehicleClassName }}</span>
          </div>
        </van-cell>
        <van-cell class="item" required v-if="vehicleType !== '3'">
          <span slot="title" class="item-title">是否外地车</span>
          <div class="item-right">
            <whether-select
              v-model="car.ecdemicVehicle"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </div>
        </van-cell>
        <van-cell class="item" required>
          <span slot="title" class="item-title">是否过户车</span>
          <div class="item-right">
            <whether-select
              v-model="car.chgOwnerFlag"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
              @change="changeChgOwnerFlag"
            />
          </div>
        </van-cell>
        <van-cell
          class="item"
          v-show="car.chgOwnerFlag === '1'"
          required
          is-link
          @click="changeDatePickerType('transferDate')"
        >
          <span slot="title" class="item-title">转登日期</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.transferDate }">
              {{ car.transferDate | date }}
            </span>
          </div>
        </van-cell>
        <!-- 非家用车车辆种类 -->
        <van-cell
          v-if="vehicleType !== '1'"
          class="item"
          @click="setPicker('carKindCode')"
          required
        >
          <span slot="title" class="item-title">车辆种类</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.carKindCode }">
              {{ $utils.findText(carKindCodes, car.carKindCode) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 上海特有字段 销售渠道 -->
        <van-cell
          class="item"
          v-if="areaCode == '3101' || areaCode == '3109'"
          required
          is-link
          @click="setPicker('salesChannel')"
        >
          <div class="item-button" @click="vehicleMoreShow = true">
            <span slot="title" class="item-title">销售渠道</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !car.salesChannel }">
                {{ $utils.findText(salesChannels, car.salesChannel) }}
              </span>
            </div>
          </div>
        </van-cell>
        <!-- 非家用车车辆使用性质 -->
        <van-cell
          class="item"
          v-if="vehicleType == '2' || vehicleType == '4'"
          @click="setPicker('useNatureCode')"
          required
        >
          <span slot="title" class="item-title">车辆使用性质</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.useNatureCode }">
              {{ $utils.findText(useNatureCodes, car.useNatureCode) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 非家用车交管车辆类型 -->
        <!-- 上海不显示该字段 TODU chenjiafeng -->
        <van-cell
          v-if="areaCode !== '3101' && vehicleType != '1'"
          class="item"
          required
          @click="setPicker('vehicleCategory')"
        >
          <span slot="title" class="item-title">交管车辆类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.vehicleCategory }">
              {{ $utils.findText(vehicleCategorys, car.vehicleCategory) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 非家用车号牌类型
          车辆种类为【客车、货车、低速载货汽车、挂车、特种车】时，默认为【小型汽车号牌】，
          车辆种类为【摩托车、拖拉机、三轮汽车】时，不显示本字段，摩托车--上海广东显示，
          广东、四川地区挪到车牌号下面，这边不显示
          TUDO chenjiafeng
        -->
        <van-cell
          class="item"
          :required="car.noLicenseFlag != 1"
          v-if="licenseTypeFlag"
          @click="
            car.noLicenseFlag != 1
              ? setPicker('licenseType')
              : $dialog.alert({ message: '未上牌时不允许录入号牌类型' })
          "
        >
          <span slot="title" class="item-title">号牌类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.licenseType }">
              {{ $utils.findText(licenseTypes, car.licenseType) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 非家用车--行驶证车型 -->
        <van-cell class="item" v-if="vehicleType != '1'" required>
          <span slot="title" class="item-title">行驶证车型</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="car.travelVehicleModel"
              placeholder="请输入"
              maxlength="50"
              @input="e => input(e, 'travelVehicleModel')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入')"
            />
          </div>
        </van-cell>
        <!-- 上海、北京特有字段 购车发票日期 -->
        <!-- 新车必录，必须小于等于保险起期、上海、北京特有字段 -->
        <van-cell
          class="item"
          v-if="areaCode == '3101' || areaCode == '3109' || areaCode == '1101'"
          :required="false"
          is-link
          @click="changeDatePickerType('carInvoiceDate')"
        >
          <span slot="title" class="item-title">购车发票日期</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.carInvoiceDate }">
              {{ car.carInvoiceDate | date }}
            </span>
          </div>
        </van-cell>
        <!-- 上海特有字段 首年投保日期 -->
        <van-cell
          class="item"
          v-if="areaCode == '3101' || areaCode == '3109'"
          is-link
          @click="changeDatePickerType('firstyearDate')"
        >
          <span slot="title" class="item-title">首年投保日期</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.firstyearDate }">
              {{ car.firstyearDate | date }}
            </span>
          </div>
        </van-cell>
        <!-- 北京特有字段 是否新车备案 -->
        <van-cell class="item" required v-if="areaCode == '1101'">
          <span slot="title" class="item-title">是否新车备案</span>
          <div class="item-right">
            <whether-select
              v-model="car.carRegiste"
              :options="[
                { text: '备案', value: '1' },
                { text: '未备案', value: '0' }
              ]"
            />
          </div>
        </van-cell>
        <!-- 北京特有字段 车辆来历凭证编号 -->
        <!-- 如果是北京地区是新车备案，车辆定型后，显示此字段 -->
        <van-field
          required
          class="item short-item"
          placeholder="请输入车辆来历凭证编号"
          v-show="
            areaCode == '1101' &&
              car.carRegiste == '1' &&
              control.vehicleModelChecked
          "
          input-align="right"
          v-model="car.certificateNo"
        >
          <span slot="label" class="item-title">车辆来历凭证编号</span>
        </van-field>
        <!-- 北京特有字段 车辆来历凭证种类 -->
        <!-- 如果是北京地区是新车备案，车辆定型后，显示此字段 -->
        <van-cell
          class="item short-item"
          v-if="
            areaCode == '1101' &&
              car.carRegiste == '1' &&
              control.vehicleModelChecked
          "
          required
          is-link
          @click="setPicker('certificateType')"
        >
          <span slot="title" class="item-title">车辆来历凭证种类</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.certificateType }">
              {{ $utils.findText(certificateTypes, car.certificateType) }}
            </span>
          </div>
        </van-cell>
        <!-- 北京特有字段 车辆来历凭证所载日期 -->
        <!-- 如果是北京地区是新车备案，车辆定型后，显示此字段。 -->
        <van-cell
          class="item short-item"
          required
          v-if="
            areaCode == '1101' &&
              car.carRegiste == '1' &&
              control.vehicleModelChecked
          "
          is-link
          @click="changeDatePickerType('certificateDate')"
        >
          <span slot="title" class="item-title">车辆来历凭证所载日期</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.certificateDate }">
              {{ car.certificateDate | date }}
            </span>
          </div>
        </van-cell>
        <!-- 北京特有字段 行驶证发证日期 -->
        <!-- 外地车标志“是”时，必录；日期不能大于当前日期 -->
        <van-cell
          class="item short-item"
          v-if="areaCode == '1101'"
          :required="car.ecdemicVehicle == '1'"
          is-link
          @click="changeDatePickerType('travelIssueDate')"
        >
          <span slot="title" class="item-title">行驶证发证日期</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.travelIssueDate }">
              {{ car.travelIssueDate | date }}
            </span>
          </div>
        </van-cell>
        <!-- 北京特有字段 单双号标识 -->
        <van-cell
          class="item"
          v-if="areaCode == '1101'"
          is-link
          @click="setPicker('restricFlag')"
        >
          <span slot="title" class="item-title">单双号标识</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.restricFlag }">
              {{ $utils.findText(restricFlags, car.restricFlag) }}
            </span>
          </div>
        </van-cell>
        <!-- 更多车辆信息 -->
        <div class="item-button" @click="vehicleMoreShow = true">
          <span>更多车辆信息</span>
          <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
        </div>
      </div>
      <div class="group-content">
        <van-cell class="group">
          <div slot="title" class="group-title">
            <img
              width="16px"
              height="16px"
              src="../../../assets/vehicle/vehicleInfo/carowner.png"
            />
            车主信息
          </div>
        </van-cell>
        <!-- 客户类型(展示隐藏) -->
        <van-cell class="item" required>
          <span slot="title" class="item-title">客户类型</span>
          <div class="item-right">
            <whether-select
              v-model="carOwner.custType"
              :options="[
                { text: '个人', value: '01' },
                { text: '单位', value: '02' }
              ]"
              @change="changeCustomerType"
            />
          </div>
        </van-cell>
        <div class="customer-group" v-show="carOwner.custType == '01'">
          <!-- 姓名 -->
          <div class="item-container">
            <img
              class="item-ocr-image"
              v-if="control.carOwnerImage"
              :src="control.carOwnerImage"
            />
            <van-cell class="item" required>
              <span slot="title" class="item-title">姓名</span>
              <div class="item-right">
                <input
                  class="item-right-input"
                  v-model="carOwner.name"
                  placeholder="请输入姓名"
                  maxlength="50"
                  @focus="e => (e.target.placeholder = '')"
                  @blur="e => (e.target.placeholder = '请输入')"
                />
              </div>
            </van-cell>
          </div>
          <!-- 证件类型 -->
          <van-cell class="item" required @click="setPicker('identityType')">
            <span slot="title" class="item-title">证件类型</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !carOwner.identityType }">
                {{ $utils.findText(identityTypes, carOwner.identityType) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
              <div
                v-show="carOwner.identityType === '111'"
                class="item-right-button"
                @click.stop="OCR('carOwner')"
              >
                <span class="item-right-button-text">证件识别</span>
                <van-icon
                  class="item-right-button-icon"
                  name="photograph"
                  color="#3B8AFE"
                />
              </div>
            </div>
          </van-cell>
          <!-- 证件号码 -->
          <div class="item-container">
            <img
              class="item-ocr-image"
              v-if="control.carOwnerIdentifyNoImage"
              :src="control.carOwnerIdentifyNoImage"
            />
            <van-cell required class="item">
              <span slot="title" class="item-title">证件号码</span>
              <div class="item-right">
                <input
                  class="item-right-input"
                  v-model="carOwner.identityNo"
                  placeholder="请输入证件号码"
                  :maxlength="identityNoMaxLength"
                  @input="e => input(e, 'identityNo')"
                  @focus="e => (e.target.placeholder = '')"
                  @blur="identityNoBlur"
                />
              </div>
            </van-cell>
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效起期 -->
            <van-cell
              v-if="areaCode == '5201' && comCName.indexOf('安顺中') !== -1"
              class="item"
              required
              :value="carOwner.identifyStartDate"
              @click="changeDatePickerType('identifyStartDate')"
            >
              <span slot="title" class="item-title">证件有效起期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !carOwner.identifyStartDate
                  }"
                >
                  {{ carOwner.identifyStartDate | date }}
                </span>
              </div>
            </van-cell>
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效止期/长期有效 -->
            <!-- 不区分证件类型，都要有 -->
            <van-cell
              v-if="areaCode == '5201' && comCName.indexOf('安顺中') !== -1"
              class="item"
              required
              @click="
                carOwnerIdentifyLongTerm
                  ? ''
                  : changeDatePickerType('identifyEndDate')
              "
            >
              <span slot="title" class="item-title">证件有效止期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !carOwner.identifyEndDate
                  }"
                >
                  {{ carOwner.identifyEndDate | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
                <van-checkbox
                  :disabled="
                    !(carOwner.identityType == '111' && carOwner.age > 46)
                  "
                  class="item-right-checkbox"
                  icon-size="14"
                  v-model="carOwnerIdentifyLongTerm"
                  @click.stop=""
                  @change="
                    v => (carOwner.identifyEndDate = v ? '9999-12-31' : '')
                  "
                >
                  <span>长期有效</span>
                </van-checkbox>
              </div>
            </van-cell>
          </div>
          <!-- 北京 特有 -->
          <!-- 出生日期 -->
          <div class="item-container" v-if="areaCode == '1101'">
            <img
              class="item-ocr-image"
              v-if="control.carOwnerBirthdayImage"
              :src="control.carOwnerBirthdayImage"
            />
            <van-cell
              class="item"
              required
              clickable
              @click="changeDatePickerType('birthday')"
            >
              <span slot="title" class="item-title">出生日期</span>
              <div class="item-right">
                <span :class="{ 'item-placeholder': !carOwner.birthday }">
                  {{ carOwner.birthday | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
          </div>
          <!-- 云南 贵州 特有 -->
          <!-- 性别 -->
          <div
            class="item-container"
            v-if="areaCode == '5201' || areaCode == '5301'"
          >
            <img
              class="item-ocr-image"
              v-if="control.carOwnerGenderImage"
              :src="control.carOwnerGenderImage"
            />
            <van-cell class="item" required>
              <span slot="title" class="item-title">性别</span>
              <div class="item-right">
                <!-- 身份证时自动带出,不可编辑 -->
                <whether-select
                  :disabled="carOwner.identityType == '111'"
                  v-model="carOwner.gender"
                  :options="[
                    { text: '男', value: '1' },
                    { text: '女', value: '2' }
                  ]"
                />
              </div>
            </van-cell>
          </div>
          <!-- 河北地区行政区域 -->
          <van-cell
            v-if="areaCode == '1301'"
            class="item"
            @click="areaListShow = true"
          >
            <span slot="title" class="item-title">行政区域</span>
            <div class="item-right">
              <span v-if="carOwner.areaDetail">
                {{ carOwner.areaDetail }}
              </span>
              <span style="color:#b9b9b9" v-else>
                投保交强险此字段必填
              </span>
            </div>
          </van-cell>
          <!-- 移动电话 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">移动电话</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="phoneNo"
                placeholder="请输入"
                maxlength="11"
                @paste="onpaste"
                @focus="phoneInputFocus"
                @blur="e => (e.target.placeholder = '请输入')"
              />
            </div>
          </van-cell>
          <!-- 云南 贵州 天津 安徽 特有 -->
          <!-- 职业 -->
          <van-cell
            v-if="
              areaCode == '5201' ||
                areaCode == '5301' ||
                areaCode == '1201' ||
                areaCode == '3401'
            "
            class="item"
            required
            @click="setPicker('occupation')"
          >
            <span slot="title" class="item-title">职业</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !carOwner.occupation }">
                {{ $utils.findText(occupations, carOwner.occupation) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 云南 贵州 天津 特有 -->
          <!-- 国籍 -->
          <van-cell
            v-if="
              areaCode == '5201' || areaCode == '5301' || areaCode == '1201'
            "
            class="item"
            required
            @click="setPicker('nationality')"
          >
            <span slot="title" class="item-title">国籍</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !carOwner.nationality }">
                {{ $utils.findText(nationalitys, carOwner.nationality) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 贵州特有 必填 -->
          <!-- 联系地址 -->
          <van-cell class="item" v-if="areaCode == '5201'" required>
            <span slot="title" class="item-title">联系地址</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="carOwner.address"
                placeholder="请输入联系地址"
                maxlength="100"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入联系地址')"
              />
            </div>
          </van-cell>
          <div></div>
        </div>
        <!-- 单位信息 -->
        <div class="customer-group" v-show="carOwner.custType == '02'">
          <!-- 单位名称 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">单位名称</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="carOwner.name"
                placeholder="请输入单位名称"
                maxlength="50"
                @focus="e => (e.target.placeholder = '')"
                @blur="e => (e.target.placeholder = '请输入单位名称')"
              />
            </div>
          </van-cell>
          <!-- 证件类型 -->
          <van-cell class="item" required @click="setPicker('identityType')">
            <span slot="title" class="item-title">证件类型</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !carOwner.identityType }">
                {{ $utils.findText(identityTypes, carOwner.identityType) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 证件号码 -->
          <div class="item-container">
            <img
              class="item-ocr-image"
              v-if="control.carOwnerIdentifyNoImage"
              :src="control.carOwnerIdentifyNoImage"
            />
            <van-cell required class="item">
              <span slot="title" class="item-title">证件号码</span>
              <div class="item-right">
                <input
                  class="item-right-input"
                  v-model="carOwner.identityNo"
                  placeholder="请输入证件号码"
                  :maxlength="identityNoMaxLength"
                  @input="e => input(e, 'identityNo')"
                  @focus="e => (e.target.placeholder = '')"
                  @blur="e => (e.target.placeholder = '请输入证件号码')"
                />
              </div>
            </van-cell>
          </div>
          <!-- 移动电话 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">移动电话</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model="phoneNo"
                placeholder="请输入"
                maxlength="11"
                @focus="phoneInputFocus"
                @blur="e => (e.target.placeholder = '请输入')"
              />
            </div>
          </van-cell>
          <div></div>
        </div>
      </div>
      <div class="next-button-container">
        <div class="next-button">
          <van-button round block type="info" @click="saveVehicleInfo">
            下一步
          </van-button>
        </div>
      </div>
    </div>
    <van-overlay
      :show="licenseNoKeyBoardShow"
      :custom-style="{ background: '#0000' }"
      @click="licenseNoKeyBoardShow = false"
    >
      <car-license-input
        v-model="licenseNo"
        :show="licenseNoKeyBoardShow"
        :maxLength="11"
        @closeCarLicenseInput="licenseNoKeyBoardClose"
      >
        <div></div>
      </car-license-input>
    </van-overlay>
    <van-popup
      v-model="vehicleMoreShow"
      :round="false"
      position="bottom"
      class="page-popup"
    >
      <vehicle-more @cancel="vehicleMoreShow = false" />
    </van-popup>
    <van-popup v-model="datePickerShow" position="bottom">
      <van-datetime-picker
        type="date"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onDatePickerConfirm"
        @cancel="datePickerType = ''"
      />
    </van-popup>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData"
        :default-index="defaultIndex"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
    <van-popup v-model="areaListShow" position="bottom" :overlay="true">
      <van-area
        :area-list="areaList"
        @confirm="areaConfirm"
        @cancel="areaListShow = false"
      />
    </van-popup>
    <van-dialog
      v-model="reSearchWithCarOwner"
      show-cancel-button
      @confirm="reSearch"
      @cancel="reSearchWithCarOwner = false"
    >
      <div style="margin: 12px;">请填写车主姓名</div>
      <van-field style="border: 1px solid #eee;" v-model="carOwner.name" />
    </van-dialog>
  </div>
</template>

<script>
import { Checkbox, Sticky, Toast, Area } from "vant";
import StepsNavBar from "@/components/stepsNavBar.vue";
import WhetherSelect from "./whetherSelect.vue";
import VehicleMore from "./vehicleMore";
import { mapMutations, mapState, mapActions } from "vuex";
import _ from "lodash";
import { beforeActive } from "../../../utils/index";
import CarLicenseInput from "../common/carLicenseInput.vue";
import { trackMap } from "@src/utils/index.js";
import {
  getArgueSolution,
  getDefaultChannel,
  getDefaultAgent,
  agentQuery,
  carInfoByFrameNo,
  carInfoByLicenseNo,
  carInfoByOrderId,
  saveToOrder
} from "@src/http/module/vehicle";
import Config from "@src/common/json/newCoreDataDictionary";
import {
  Risk,
  Plan,
  RelationPeople
} from "../../../store/modules/vehicle/vehicle";
import province_list from "@src/common/json/area.js";
const ValidateRules = {
  //渠道信息
  "projectChannel.businessNatureCode": { required: "代理信息" },
  //车辆信息
  "car.frameNo": { required: "车架号" }, //, vin: "不符合规则"
  "car.enrollDate": { required: "初登日期" },
  "car.engineNo": { required: "发动机号" },
  "car.vehicleBrand": { required: "厂牌型号" },
  "car.seatCount": { required: "核定载客数" },
  // "car.ecdemicVehicle": { required: "是否外地车" },
  // "car.chgOwnerFlag": { required: "是否过户车" },
  "car.purchasePrice": { required: "新车购置价" },
  // actualValue: { required: "车辆实际价" },
  "car.fuelType": { required: "能源类型" },
  // net: { required: "整备质量" },
  // exhaustScale: { required: "排量" },
  // "car.vehicleCategory": { required: "交管车辆类型" },
  "car.travelVehicleModel": { required: "行驶证车型" },
  // poCategory: { required: "行驶证车辆类型" },
  "car.specialCarFlag": { required: "特有车投保标志" },
  //车主信息
  "carOwner.name": { required: "姓名", name: "不符合规则" },
  "carOwner.identityType": { required: "证件类型" },
  "carOwner.identityNo": { required: "证件号码" },
  "carOwner.phoneNo": { required: "手机号码", mobile2: "不正确" }
};
export default {
  components: {
    CarLicenseInput,
    [Sticky.name]: Sticky,
    [StepsNavBar.name]: StepsNavBar,
    [Checkbox.name]: Checkbox,
    [WhetherSelect.name]: WhetherSelect,
    [VehicleMore.name]: VehicleMore,
    [Area.name]: Area
  },
  data() {
    // let { params = {}, query = {} } = this.$route;
    // let { riskCode: riskCode1 } = query;
    // let { riskCode: riskCode2 } = params;
    // let riskCode = riskCode1 || riskCode2 || "DEA";
    return {
      vehicleType: "",
      // riskCode,
      isAgentUser: false,
      vehicleMoreShow: false,
      datePickerType: "",
      dataPickerType: "",
      pickerData: [],
      businessNatures: Object.keys(Config.businessNature).map(key => ({
        text: Config.businessNature[key],
        value: key
      })),
      occupations: Object.keys(Config.profession).map(key => ({
        text: Config.profession[key],
        value: key
      })),
      //countryCodeTJ nationality nationality2
      nationalitys: Object.keys(Config.nationality).map(key => ({
        text: Config.nationality[key],
        value: key
      })),
      //号牌种类
      // licenseTypes: Object.keys(Config.licenseType).map(key => ({
      //   text: Config.licenseType[key],
      //   value: key
      // })),
      //销售渠道
      salesChannels: Object.keys(Config.producerCode).map(key => ({
        text: Config.producerCode[key],
        value: key
      })),
      //车辆来历凭证种类
      certificateTypes: Object.keys(Config.certificateType).map(key => ({
        text: Config.certificateType[key],
        value: key
      })),
      //单双号标识
      restricFlags: Object.keys(Config.restricFlag).map(key => ({
        text: Config.restricFlag[key],
        value: key
      })),
      check: {},
      currentDate: new Date(),
      minDate: new Date(),
      maxDate: new Date(),
      isPreset: false,
      areaCode: "",
      areaListShow: false,
      areaList: province_list,
      licenseNoKeyBoardShow: false,
      comCode: "",
      comCName: "",
      defaultIndex: 0,
      reSearchType: ""
    };
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (from.name == "insureOrderDetail") {
        vm.control.from = "insureOrderDetail";
      } else if (from.name == "orderLists" || from.name == "orderDetailCopy") {
        vm.control.from = "orderLists";
      } else if (
        from.name == "renewalList" ||
        from.name == "renewalContactDetails"
      ) {
        vm.control.from = "renewalList";
      } else if (from.name == "selectVehicle") {
        vm.control.from = "selectVehicle";
      }
    });
  },
  beforeRouteLeave(to, from, next) {
    next();
  },
  activated() {
    console.warn("activated start", this.vehicleMoreShow);
    this.licenseNoKeyBoardShow = false;
    this.vehicleMoreShow = false;
    this.datePickerShow = false;
    this.dataPickerShow = false;
    this.areaListShow = false;
    this.vehicleType = this.$store.state.vehicle.vehicleType;
    if (!this.vehicleType) {
      // 获取vehicleType TUDO chenjiafeng
      this.vehicleType = this.$GetRequest("vehicleType", true);
      if (
        !this.vehicleType ||
        !["1", "2", "3", "4"].includes(this.vehicleType)
      ) {
        this.vehicleType = "1";
      }
      // 存储vehicleType TUDO chenjiafeng
      this.setVehicleType(this.vehicleType);
    }
    let userInfo = this.localStorage.get("userInfo", {});
    let comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    this.comCName = userInfo.comCName || "";
    if (this.control.vehicleInfoInit) return;
    let loading = Toast.loading();
    // beforeActive().then(() => {
    let { params = {}, query = {} } = this.$route;
    let { orderId } = params;
    let { orderId: id } = query;
    orderId = orderId || id;
    if (this.control.vehicleInfoInit) return;
    orderId = orderId || this.orderId;
    console.warn("跳转 带来的订单id : ", orderId);
    userInfo = this.localStorage.get("userInfo", {});
    comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    if (orderId) {
      this.setOrderId(orderId);
      this.carInfoByOrderId();
    } else {
      const defaultChannel = {
        isProxy: "1"
      };
      // 车辆使用性质 TUDO chenjiafeng
      let useNatureCode = "";
      if (this.vehicleType == "1") {
        useNatureCode = "85";
      } else if (this.vehicleType == "2") {
        useNatureCode = "220";
      } else if (this.vehicleType == "3") {
        useNatureCode = "000";
      } else {
        useNatureCode = "100";
      }
      // 车辆种类 TUDO chenjiafeng
      let carKindCode = "";
      if (this.vehicleType == "1" || this.vehicleType == "2") {
        carKindCode = "11";
      } else if (this.vehicleType == "3") {
        carKindCode = "52";
      } else {
        carKindCode = "42";
      }
      let carRegiste = "";
      if (this.areaCode == "1101") {
        carRegiste = "1";
      }
      // 号牌种类 TUDO chenjiafeng
      let licenseType = "";
      if (this.vehicleType == "3") {
        licenseType = "24";
      } else {
        licenseType = "02";
      }
      // 交管车辆类型 TUDO chenjiafeng
      let vehicleCategory = "";
      if (this.vehicleType == "1") {
        vehicleCategory = "K33";
      } else if (this.vehicleType == "2") {
        vehicleCategory = "K31";
      } else if (this.vehicleType == "3") {
        vehicleCategory = "M21";
      } else {
        vehicleCategory = "Z11";
      }
      // 号牌底色 TUDO chenjiafeng
      let licenseColorCode = "";
      if (this.vehicleType == "3") {
        licenseColorCode = "3";
      } else {
        licenseColorCode = "1";
      }
      const defaultCarInfo = {
        enrollDate: "", //new Date().format("yyyy-MM-dd 00:00:00")
        noLicenseFlag: "0",
        ecdemicVehicle: "0",
        chgOwnerFlag: "0",
        purchasePrice: "0",
        actualValue: "0",
        userYear: "0",
        fuelType: "D1",
        vehicleCategory: vehicleCategory,
        carKindCode: carKindCode,
        useNatureCode: useNatureCode,
        licenseType: licenseType,
        licenseColorCode: licenseColorCode,
        isAboutAgri: "0",
        loanVehicleFlag: "0",
        carCheckStatus: "0",
        specialCarFlag: "0",
        argueSolution: "01",
        poCategory: "K01",
        carRegiste
      };
      let occupation = "";
      if (
        this.areaCode == "5201" ||
        this.areaCode == "5301" ||
        this.areaCode == "1201" ||
        this.areaCode == "3401"
      ) {
        //显示职业的地区
        occupation = "900";
      }
      if (
        this.areaCode == "5201" ||
        this.areaCode == "5301" ||
        this.areaCode == "1201"
      ) {
        //显示国籍的地区
      }
      const defaultCarOwner = {
        gender: this.vehicleType == "1" ? "1" : "",
        identityType: this.vehicleType == "1" ? "111" : "810",
        custType: this.vehicleType == "1" ? "01" : "02",
        nationality: "CHN",
        occupation
      };
      var configParams = this.localStorage.get("ConfigParams");
      if (configParams && configParams.licenseNoAbbr) {
        this.car.licenseNo = configParams.licenseNoAbbr;
        //.charAt(0);
      }
      //全国其他地区，都是默认“是”，可修改。
      this.setProjectChannel(defaultChannel);
      /**
        businessNature: "0101",
        businessNatureCode: "0101",
        businessNatureName: "业务员销售（直销）"
       */
      this.setCarInfo({
        ...defaultCarInfo
        // enrollDate: new Date().format("yyyy-MM-dd 00:00:00")
      });
      this.setCarOwner(defaultCarOwner);
      this.getDefaultChannel();
      this.getDefaultArgueSolution();
    }
    loading.clear();
    this.control.vehicleInfoInit = true;
    // });
  },
  deactivated() {
    // console.warn("deactivated start", this.vehicleMoreShow);
    // console.warn("deactivated end", this.vehicleMoreShow);
  },
  created() {
    //埋点 首页进来
    this.$GetRequest("source", true) === "shell" &&
      trackMap("c_home_car_insure", "车险投保", {
        c_salesperson_type: "Y",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "首页"
      });
  },
  mounted() {
    if (this.$isAgent()) {
      this.isAgentUser = true;
    } else {
      this.isAgentUser = false;
    }
  },
  filters: {
    date(date) {
      return (date || "").split(" ")[0].replace(/-/g, "/") || "请选择";
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      control: ({ control }) => control,
      car: ({ car }) => car,
      carOwner: ({ carOwner }) => carOwner,
      agent: ({ agent }) => agent,
      projectChannel: ({ projectChannel }) => projectChannel,
      policyHolder: ({ policyHolder }) => policyHolder
    }),
    ...mapState("channel", {
      channel: ({ channel }) => channel
    }),
    riskCode() {
      switch (this.vehicleType) {
        case "1":
          return "DEA";
        case "2":
          return "DEA";
        case "3":
          return "DEC";
        case "4":
          return "DEB";
        default:
          return "DEA";
      }
    },
    reSearchWithCarOwner: {
      get: function() {
        return !!this.reSearchType;
      },
      set: function(v) {
        if (!v) {
          this.reSearchType = "";
        }
      }
    },
    carOwnerIdentifyLongTerm: {
      get: function() {
        return this.carOwner.identifyLongTerm == "1";
      },
      set: function(v) {
        this.carOwner.identifyLongTerm = v ? "1" : "0";
      }
    },
    identityTypes() {
      let identityTypes = [];
      if (this.carOwner.custType == "01") {
        Object.keys(Config.identifyType01).map(value => {
          let text = Config.identifyType01[value];
          //上海,北京无户口本
          if (text == "户口簿") {
            if (
              this.areaCode == "1101" ||
              this.areaCode == "3101" ||
              this.areaCode == "3109"
            ) {
              return;
            }
          }
          // if (text == "港澳台居民居住证") {
          //   //非上海
          //   if (!(this.areaCode == "3101" || this.areaCode == "3109")) {
          //     return;
          //   }
          // }
          identityTypes.push({ text, value });
        });
        if (this.areaCode == "3101" || this.areaCode == "3109") {
          identityTypes.push({ text: "港澳居民来往内地通行证", value: "516" });
          identityTypes.push({ text: "台湾居民来往内地通行证", value: "511" });
        }
      } else {
        Object.keys(Config.identifyType02).map(value => {
          let text = Config.identifyType02[value];
          identityTypes.push({ text, value });
        });
      }
      return identityTypes;
    },
    // isAgentUser() {
    //   const userInfo = this.localStorage.get('userInfo', {})
    //   const agentCode = userInfo.agentCode || ''
    //   return !!agentCode
    // },
    // 广东
    localVehicle() {
      // 本地车已上牌车辆定义：广东本地承保车牌为粤开头，但不包含深圳车（粤B）、外地车、未上牌车、港澳车（粤Z）、使领馆车
      if (this.areaCode === "4401") {
        if (
          this.licenseNo.includes("粤") &&
          !this.licenseNo.includes("粤B") &&
          !this.licenseNo.includes("粤Z") &&
          !this.licenseNo.includes("使") &&
          !this.licenseNo.includes("领") &&
          !this.noLicenseFlag
        ) {
          return true;
        }
      }
      return false;
    },
    addressRequired() {
      //安徽、河南地区必填
      if (this.areaCode == "3401" || this.areaCode == "4101") {
        return true;
      }
      return false;
    },
    noLicenseFlag: {
      get: function() {
        return this.car.noLicenseFlag === "1";
      },
      set: function(value) {
        this.car.noLicenseFlag = value ? "1" : "0";
        if (value) {
          //如果勾选未上牌，号牌类型清空，且不能修改(除深圳) TUDO chenjiafeng
          if (this.areaCode != "4403") {
            this.car.licenseType = "";
          }
          // this.car.licenseNo = "";
          //如果归属机构为江苏、上海、北京，勾选了未上牌，则外地车标志默认为“否”，可修改。
          if (
            this.areaCode == "3201" ||
            this.areaCode == "3101" ||
            this.areaCode == "3109" ||
            this.areaCode == "1101"
          ) {
            this.car.ecdemicVehicle = "0";
          }
          //广东地区号牌类型、初登日期、车架号、厂牌型号、未上牌改为已上牌修改需要重新定型
          if (this.areaCode == "4401") {
            this.control.vehicleModelChecked = false;
          }
        } else {
          //如果未勾选未上牌，号牌类型赋默认值(除深圳)，且能修改 TUDO chenjiafeng
          if (this.areaCode != "4403") {
            if (this.vehicleType !== "3") {
              this.car.licenseType = "02";
            } else if (this.vehicleType == "3") {
              //摩托车
              if (["51", "52", "53"].includes(this.car.carKindCode)) {
                this.car.licenseType = "24";
              }
              // 拖拉机
              if (["61", "62", "63", "64"].includes(this.car.carKindCode)) {
                this.car.licenseType = "14";
              }
            }
          }
          // @deprecated 车牌号不变
          // console.warn("改变未上牌标志 : ", value);
          // let configParams = this.localStorage.get("ConfigParams");
          // if (configParams && configParams.licenseNoAbbr) {
          //   this.car.licenseNo = configParams.licenseNoAbbr.charAt(0);
          // }
        }
        this.control.vehicleModelChecked = false;
      }
    },
    licenseNo: {
      get: function() {
        return this.car.licenseNo;
      },
      set: function(licenseNo) {
        this.car.licenseNo = licenseNo.toUpperCase();
        // if (this.car.licenseNo.length > 8) {
        //   this.car.licenseType = "52";
        // }
      }
    },
    frameNo: {
      get: function() {
        return this.car.frameNo;
      },
      set: function(frameNo) {
        this.car.frameNo = frameNo;
        frameNo = frameNo.replace(/[^A-Za-z0-9]/g, "").toUpperCase();
        this.car.frameNo = frameNo;
        this.control.vehicleModelChecked = false;
      }
    },
    engineNo: {
      get: function() {
        return this.car.engineNo;
      },
      set: function(engineNo) {
        this.car.engineNo = engineNo;
        engineNo = engineNo.toUpperCase();
        this.car.engineNo = engineNo;
        // 非广东
        if (this.areaCode != "4401") {
          this.control.vehicleModelChecked = false;
        }
      }
    },
    vehicleBrand: {
      get: function() {
        return this.car.vehicleBrand;
      },
      set: function(vehicleBrand) {
        this.car.vehicleBrand = vehicleBrand;
        // NOTE 2020年06月19日10:07:17 @操哥 bug#6798跟需求冲突;
        // vehicleBrand = vehicleBrand.toUpperCase();
        // this.car.vehicleBrand = vehicleBrand;
        this.control.vehicleModelChecked = false;
        //广东地区号牌类型、初登日期、车架号、厂牌型号、未上牌改为已上牌修改需要重新定型
        if (this.areaCode == "4401") {
          this.control.vehicleModelChecked = false;
        }
      }
    },
    seatCount: {
      get: function() {
        return this.car.seatCount;
      },
      set: function(seatCount) {
        this.car.seatCount = seatCount;
        seatCount = seatCount.replace(/[^0-9]/g, "").toUpperCase();
        this.car.seatCount = seatCount;
      }
    },
    phoneNo: {
      get() {
        if (this.carOwner.phoneNo.length < 11 || !this.isPreset) {
          return this.carOwner.phoneNo;
        }
        return (
          this.carOwner.phoneNo.substr(0, 3) +
          "****" +
          this.carOwner.phoneNo.substr(7)
        );
      },
      set(v) {
        if (v.match(/\*/)) {
          if (v.length != 11) {
            this.carOwner.phoneNo = "";
          }
          return;
        }
        this.carOwner.phoneNo = v;
        this.carOwner.phoneNo = v.replace(/[^0-9]/g, "");
      }
    },
    datePickerShow: {
      get: function() {
        return !!this.datePickerType;
      },
      set: function() {
        this.datePickerType = "";
      }
    },
    dataPickerShow: {
      get: function() {
        return !!this.pickerData.length;
      },
      set: function() {
        this.pickerData = [];
        this.dataPickerType = "";
      }
    },
    identityNoMaxLength() {
      switch (this.carOwner.identityType) {
        case "111":
          return 18;
        default:
          return 32;
      }
    },
    // 号牌类型
    licenseTypes() {
      if (
        ["11", "21", "22", "31"].includes(this.car.carKindCode) ||
        this.vehicleType == "4"
      ) {
        return Object.keys(Config.licenseTypes).map(key => ({
          text: Config.licenseTypes[key],
          value: key
        }));
      }
      // 上海广东-摩托车
      if (this.areaCode == "4401" || this.areaCode == "3101") {
        if (["51", "52", "53"].includes(this.car.carKindCode)) {
          return Object.keys(Config.licenseTypeMo).map(key => ({
            text: Config.licenseTypeMo[key],
            value: key
          }));
        }
      }
      // 四川
      if (this.areaCode == "5101") {
        if (["51", "52", "53"].includes(this.car.carKindCode)) {
          return [{ text: "警用摩托车号牌/公安民用号牌", value: "24" }];
        }
      }
      // 广东、四川，拖拉机时对应的号牌类型枚举
      if (this.areaCode == "4401" || this.areaCode == "5101") {
        if (["61", "62", "63", "64"].includes(this.car.carKindCode)) {
          return [
            { text: "农用运输车号牌", value: "13" },
            { text: "拖拉机号牌", value: "14" }
          ];
        }
      }
      return [];
    },
    //车辆种类
    carKindCodes() {
      if (this.areaCode == "3201" && this.vehicleType == "3") {
        //江苏 vehicleType = 3时，去除一些枚举值 TUDO chenjiafeng
        return Object.keys(Config["carKindCodeJS" + this.vehicleType]).map(
          key => ({
            text: Config["carKindCodeJS" + this.vehicleType][key],
            value: key
          })
        );
      }
      if (
        (this.areaCode == "3201" ||
          this.areaCode == "3301" ||
          this.areaCode == "3302") &&
        this.vehicleType == "2"
      ) {
        //江苏，浙江，宁波 vehicleType = 2时，枚举值去除 低速载货汽车 TUDO chenjiafeng
        return Object.keys(Config["carKindCode" + this.vehicleType])
          .map(key => ({
            text: Config["carKindCode" + this.vehicleType][key],
            value: key
          }))
          .filter(item => item.value !== "22");
      }
      //默认枚举值，随vehicleType联动 TUDO chenjiafeng
      return Object.keys(Config["carKindCode" + this.vehicleType]).map(key => ({
        text: Config["carKindCode" + this.vehicleType][key],
        value: key
      }));
    },
    // 车辆使用性质 TUDO chenjiafeng
    useNatureCodes() {
      switch (this.vehicleType) {
        case "4":
          return Object.keys(Config["useNatureCode" + this.vehicleType]).map(
            key => ({
              text: Config["useNatureCode" + this.vehicleType][key],
              value: key
            })
          );
        case "2":
          return Object.keys(
            Config["useNatureCode" + this.car.carKindCode]
          ).map(key => ({
            text: Config["useNatureCode" + this.car.carKindCode][key],
            value: key
          }));
        default:
          return [];
      }
    },
    // 车辆交管类型与车辆使用性质联动 TUDO chenjiafeng
    vehicleCategorys() {
      if (this.car.useNatureCode) {
        // 家用车、货车/客车/挂车
        if (this.vehicleType == "1" || this.vehicleType == "2") {
          // 车辆使用性质-106-营业挂车、250非营业挂车
          if (
            this.car.useNatureCode == "106" ||
            this.car.useNatureCode == "250"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS106).map(key => ({
                text: Config.vehicleCategoryJS106[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory106).map(key => ({
                text: Config.vehicleCategory106[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-104营业货车
          if (this.car.useNatureCode == "104") {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS104).map(key => ({
                text: Config.vehicleCategoryJS104[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory104).map(key => ({
                text: Config.vehicleCategory104[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-240-非营业货运
          if (this.car.useNatureCode == "240") {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS240).map(key => ({
                text: Config.vehicleCategoryJS240[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory240).map(key => ({
                text: Config.vehicleCategory240[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-95预约出租客运、101-营业出租租赁、85-家庭自用、220-非营业企业、230-非营业机关
          if (
            this.car.useNatureCode == "95" ||
            this.car.useNatureCode == "101" ||
            this.car.useNatureCode == "85" ||
            this.car.useNatureCode == "220" ||
            this.car.useNatureCode == "230"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS95).map(key => ({
                text: Config.vehicleCategoryJS95[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory95).map(key => ({
                text: Config.vehicleCategory95[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-102营业城市公交、103营业公路客运、105-旅游客运
          if (
            this.car.useNatureCode == "102" ||
            this.car.useNatureCode == "103" ||
            this.car.useNatureCode == "105"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS102).map(key => ({
                text: Config.vehicleCategoryJS102[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory102).map(key => ({
                text: Config.vehicleCategory102[key],
                value: key
              }));
            }
          }
        }
        // 特种车
        if (this.vehicleType == "4") {
          // 100-营业、200-非营业
          if (
            this.car.useNatureCode == "100" ||
            this.car.useNatureCode == "200"
          ) {
            if (this.areaCode == "3201") {
              //特种车，江苏--车辆交管类型枚举
              return Object.keys(Config.vehicleCategoryJS100).map(key => ({
                text: Config.vehicleCategoryJS100[key],
                value: key
              }));
            } else if (this.areaCode == "3101" || this.areaCode == "1101") {
              //特种车，上海北京--车辆交管类型枚举
              let vehicleCategoryJS100 = Object.keys(
                Config.vehicleCategoryJS100
              ).map(key => ({
                text: Config.vehicleCategoryJS100[key],
                value: key
              }));
              // 北京上海不包括 S-特种作业专用车、Z-专项作业车、Z12-大型载货专项作业车
              vehicleCategoryJS100 = vehicleCategoryJS100.filter(
                item =>
                  item.value !== "S" &&
                  item.value !== "Z" &&
                  item.value !== "Z12"
              );
              return vehicleCategoryJS100;
            } else {
              // 全国含特殊地区
              return Object.keys(Config.vehicleCategory100).map(key => ({
                text: Config.vehicleCategory100[key],
                value: key
              }));
            }
          }
        }
        // 摩托车/拖拉机/三轮汽车
        if (this.vehicleType == "3") {
          return Object.keys(Config.vehicleCategory3).map(key => ({
            text: Config.vehicleCategory3[key],
            value: key
          }));
        }
      }
      return [];
    },
    licenseTypeFlag() {
      if (this.vehicleType == "1") {
        return false;
      } else {
        if (this.vehicleType == "3") {
          if (this.areaCode == "4401" || this.areaCode == "3101") {
            if (["51", "52", "53"].includes(this.car.carKindCode)) {
              return true;
            }
          } else {
            return false;
          }
        } else {
          return true;
        }
      }
      return false;
    }
  },
  watch: {
    "car.exhaustScale"(exhaustScale) {
      // 排量小于1000:K01: "9坐（含）以下且排量1.0升（含）以下的乘用车",
      // 排量大于等于1000小于1600:K02: "9坐（含）以下且排量1.0升以上至1.6升（含）的乘用车",
      // 排量大于等于1600小于2000:K03: "9坐（含）以下且排量1.6升以上至2.0升（含）的乘用车",
      // 排量大于等于2000小于2500:K04: "9坐（含）以下且排量2.0升以上至2.5升（含）的乘用车",
      // 排量大于等于2500小于3000:K05: "9坐（含）以下且排量2.5升以上至3.0升（含）的乘用车",
      // 排量大于等于3000小于4000:K06: "9坐（含）以下且排量3.0升以上至4.0升（含）的乘用车",
      // 排量大于等于4000:K07: "9坐（含）以下且排量4.0升以上的 乘用车",
      if (exhaustScale < 1000) {
        this.car.poCategory = "K01";
      } else if (exhaustScale < 1600) {
        this.car.poCategory = "K02";
      } else if (exhaustScale < 2000) {
        this.car.poCategory = "K03";
      } else if (exhaustScale < 2500) {
        this.car.poCategory = "K04";
      } else if (exhaustScale < 3000) {
        this.car.poCategory = "K05";
      } else if (exhaustScale < 4000) {
        this.car.poCategory = "K06";
      } else {
        this.car.poCategory = "K07";
      }
    },
    "car.arbitBoardcode"(arbitBoardcode) {
      this.car.arbitBoardname = Config.arbitBoardname[arbitBoardcode];
    },
    "car.enrollDate"(...args) {
      /**计算车辆使用年限*/
      if (args.length > 1) {
        let dates = args.map(t => t.substring(0, 10));
        let set = new Set(dates);
        if (set.size > 1) {
          this.calcUseYears();
        }
      } else {
        this.calcUseYears();
      }
      //广东地区号牌类型、初登日期、车架号、厂牌型号、未上牌改为已上牌修改需要重新定型
      if (this.areaCode == "4401") {
        this.control.vehicleModelChecked = false;
      }
    },
    "projectChannel.businessNatureCode"(businessNatureCode) {
      let businessNatures = Object.keys(Config.allbusinessNature).map(key => ({
        text: Config.allbusinessNature[key],
        value: key
      }));
      this.projectChannel.businessNatureName = this.$utils.findText(
        businessNatures,
        businessNatureCode
      );
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "initVehicle",
      "setCarInfo",
      "initCarOwner",
      "setCarOwner",
      "calcUseYears",
      "initProjectChannel",
      "setProjectChannel",
      "setOrderId",
      "setPolicyHolder",
      "setRecognizee",
      "setHistoryPolicy",
      "setVehicleType"
    ]),
    ...mapMutations("channel", ["setChannel"]),
    ...mapActions("vehicle", ["getTaxConfig"]),
    onpaste(e) {
      var pastedText = undefined;
      pastedText = e.clipboardData.getData("Text");
      console.warn(pastedText, e.clipboardData);
      this.phoneNo = pastedText.trim().replace(/\s/g, "");
    },
    inputValidate(rules, value) {
      const { status, msg } = this.$validate(rules, value);
      if (!status) {
        this.$toast(msg);
      }
    },
    changeAgentChannel(isProxy) {
      this.initProjectChannel({ isProxy });
      this.getDefaultChannel();
    },
    getDefaultChannel() {
      getDefaultChannel({ isProxy: this.projectChannel.isProxy })
        .then(
          res => {
            if (res.code === "1") {
              if (res.data.isProxy) {
                this.setProjectChannel(res.data);
                if (this.$isAgent()) return (this.isAgentUser = true);
                if (res.data.isProxy == "1") {
                  this.getDefaultAgent();
                }
              } else {
                if (this.$isAgent()) return (this.isAgentUser = true);
                if (this.projectChannel.isProxy == "1") {
                  this.getDefaultAgent();
                } else {
                  this.setProjectChannel({
                    isProxy: "0",
                    businessNature: "0101",
                    businessNatureCode: "0101",
                    businessNatureName: "业务员销售（直销）"
                  });
                }
              }
            } else {
              this.$dialog.alert({ message: res.msg });
            }
          },
          error => {}
        )
        .finally(() => {
          console.warn("代理人用户", this.$isAgent());
          if (this.$isAgent()) {
            const userInfo = this.localStorage.get("userInfo", {});
            this.projectChannel.isProxy = "1";
            this.projectChannel.agentCode = userInfo.agentCode;
            this.projectChannel.agentName = userInfo.agentName;
            this.projectChannel.businessNatureCode = "2401";
            this.projectChannel.agentAgreementCode = "";
            this.projectChannel.agentAgreementName = "";
            this.agentQuery();
            this.isAgentUser = true;
          } else {
            this.isAgentUser = false;
          }
        });
    },
    getDefaultAgent() {
      getDefaultAgent({}).then(
        res => {
          if (res.code === "1") {
            if (res.data.isDefault) {
              this.projectChannel.agentName = res.data.agentName;
              this.projectChannel.agentCode = res.data.agentCode;
              this.projectChannel.businessNature = res.data.businessSource;
              this.projectChannel.businessNatureCode = res.data.businessSource;
              this.projectChannel.agentAgreementCode =
                res.data.agentAgreementCode;
              this.projectChannel.agentAgreementName =
                res.data.agentAgreementName;
              this.projectChannel.permitNo = res.data.permitNo;
              this.projectChannel.exhibitionNo = res.data.exhibitionNo;
              for (const key in res.data) {
                if (res.data.hasOwnProperty(key)) {
                  const value = res.data[key];
                  if (this.projectChannel.hasOwnProperty(key) && value) {
                    this.projectChannel[key] = value;
                  }
                }
              }
              this.agentQuery();
            }
          }
        },
        error => {}
      );
    },
    agentQuery() {
      /**requestType
       * 01:查询代理人协议信息
       * 02:查询业务风险分类 （N码）
       * 03:查询代理人协议信息（模糊查询）
       */
      // let params = {
      //   requestType: "02",
      //   agentCode: "8000189135" || this.agent.agentCode,
      //   comCode: "01",
      //   businessNature: "01",
      //   riskCode: "01"
      // };
      let userInfo = this.localStorage.get("userInfo", {});
      //1 不代理 2 代理
      let requestType = this.projectChannel.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.projectChannel.isProxy == "0"
          ? userInfo.userCode
          : this.projectChannel.agentCode;
      let businessSource = this.projectChannel.businessNatureCode;
      if (!agentCode || !businessSource) {
        console.warn("无代理人, 无业务来源");
        return;
        // return this.getDefaultAgent();
      }
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: this.riskCode,
        businessSource,
        agentCode,
        agreementNo: "", // 协议号默认传空
        agreementName: this.projectChannel.agentAgreementName
      };
      agentQuery(params)
        .then(
          res => {
            if (res.code === "1" && res.data) {
              let { salesMainMsgDto = {} } = res.data;
              this.requestData = salesMainMsgDto;
              let {
                salesAgentMsgDto = {},
                salesAgreementMsgList = []
              } = salesMainMsgDto;
              let agreementNo, agreementName;
              let { agentName, businessNature } = salesAgentMsgDto;
              let agreementIsVaild = false;
              if (Array.isArray(salesAgreementMsgList)) {
                salesAgreementMsgList.forEach(item => {
                  if (
                    item.agreementNo == this.projectChannel.agentAgreementCode
                  ) {
                    agreementIsVaild = true;
                    agreementNo = item.agreementNo;
                    agreementName = item.agreementName;
                  }
                });
              }
              this.projectChannel.agentCode = agentCode; //归属机构
              this.projectChannel.agentName = agentName; //归属机构
              this.projectChannel.businessNatureCode =
                businessNature || this.projectChannel.businessNatureCode; //业务来源
              for (const key in salesAgentMsgDto) {
                if (salesAgentMsgDto.hasOwnProperty(key)) {
                  const value = salesAgentMsgDto[key];
                  if (this.projectChannel.hasOwnProperty(key) && value) {
                    this.projectChannel[key] = value;
                  }
                }
              }
              let businessNatures = Object.keys(Config.allbusinessNature).map(
                key => ({
                  text: Config.allbusinessNature[key],
                  value: key
                })
              );
              this.projectChannel.businessNatureName = this.$utils.findText(
                businessNatures,
                this.projectChannel.businessNatureCode
              );

              if (!agreementIsVaild) {
                //代理人协议无效
                if (
                  Array.isArray(salesAgreementMsgList) &&
                  salesAgreementMsgList.length
                ) {
                  //默认首个有效
                  this.projectChannel.agentAgreementCode =
                    salesAgreementMsgList[0].agreementNo;
                  this.projectChannel.agentAgreementName =
                    salesAgreementMsgList[0].agreementName;
                } else {
                  //无代理协议
                  this.projectChannel.agentAgreementCode = "";
                  this.projectChannel.agentAgreementName = "";
                }
              } else {
                this.projectChannel.agentAgreementCode = agreementNo; //代理人协议号
                this.projectChannel.agentAgreementName = agreementName; //代理人协议名称
              }
            } else {
              if (this.projectChannel.isProxy == "1") {
                const agentName = this.projectChannel.agentName || "";
                const hintName = agentName ? `【${agentName}】` : "";
                const hint = `<p style='color:#F00'>代理人${hintName}不可用，请检查代理人状态或者更换其他代理人</p>\n`;

                this.projectChannel.agentAgreementCode = "";
                this.projectChannel.agentAgreementName = "";
                this.$dialog
                  .alert({ message: hint + res.msg })
                  .then(this.toVehicleChannel);
              }
            }
          },
          error => {}
        )
        .catch(error => {});
    },
    getDefaultArgueSolution() {
      getArgueSolution({}).then(
        res => {
          if (res.code === "1" && res.data) {
            this.setCarInfo({
              argueSolution: res.data.argueSolution,
              arbitBoardcode: res.data.arbitBoardCode
            });
          }
        },
        error => {}
      );
    },
    licenseNoClick() {
      if (!this.noLicenseFlag) {
        this.licenseNoKeyBoardShow = true;
      }
    },
    licenseNoKeyBoardClose() {
      this.licenseNoKeyBoardShow = false;
      this.licenseNoBlur();
    },
    licenseNoFocus(e) {
      if (this.noLicenseFlag) return;
      e.target.placeholder = "";
      const el = e.target;
      const pos = this.car.licenseNo ? this.car.licenseNo.length : 0;
      if (el.setSelectionRange) {
        //Firefox/Safari/Chrome/Opera
        if (el.selectionStart != 0) {
          console.warn(el.selectionStart);
        } else {
          el.setSelectionRange(pos, pos);
        }
      } else if (el.createTextRange) {
        // IE
        var range = el.createTextRange();
        range.collapse(true);
        range.moveEnd("character", pos);
        range.moveStart("character", pos);
        range.select();
      }
    },
    // 监听车牌号输入
    licenseNoBlur() {
      var target = this.$refs.licenseNo;
      target.placeholder = "请输入车牌号";
      if (
        this.car.noLicenseFlag == "0" &&
        this.licenseNoKeyBoardShow == false
      ) {
        // this.car.licenseNo = this.car.licenseNo.replace(
        //   /[^京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领挂学警港澳A-Za-z0-9]/g,
        //   ""
        // );
        const rules = { required: "车牌号", licenseNo2: "不符合规则" };
        const { status, msg } = this.$validate(rules, this.car.licenseNo);
        if (!status) {
          this.$toast(msg);
        }
      }
      //非广东
      if (this.areaCode != "4401" && this.licenseNoKeyBoardShow == false) {
        this.control.vehicleModelChecked = false;
      }
      //如果归属机构为江苏、上海、北京
      if (
        (this.areaCode == "3201" ||
          this.areaCode == "3101" ||
          this.areaCode == "3109" ||
          this.areaCode == "1101") &&
        this.licenseNoKeyBoardShow == false
      ) {
        //如果已上牌
        if (
          this.car.noLicenseFlag == "0" &&
          this.licenseNoKeyBoardShow == false
        ) {
          if (/^[苏|沪|京]/.test(this.car.licenseNo)) {
            //号牌号码为“苏”、“沪或“京”开头，默认为“否”；
            this.car.ecdemicVehicle = "0";
          } else {
            //号牌号码不是“苏”、“沪或“京”开头，则默认为“是”，都可修改。
            this.car.ecdemicVehicle = "1";
          }
        }
      }
    },
    frameNoBlur(e) {
      e.target.placeholder = "请输入车架号";
      if (this.localVehicle) return;
      const { status, msg } = this.$validate(
        { required: "车架号", vin: "不符合规则" },
        this.car.frameNo
      );
      if (!status) {
        this.$toast(msg);
      }
    },
    identityNoBlur(e) {
      if (this.carOwner.identityType == "111") {
        const { status, msg } = this.$validate(
          { idcard: "不符合规则" },
          this.carOwner.identityNo
        );
        if (!status) {
          this.$toast(msg);
          e.target.focus();
        }
      }
      e.target.placeholder = "请输入证件号码";
    },
    changeChgOwnerFlag(flag) {
      this.car.specialCarFlag = flag;
      if (flag == "0") {
        this.car.transferDate = "";
      }
    },
    isValidDate(date) {
      return date instanceof Date && !isNaN(date.getTime());
    },
    changeCustomerType(custType) {
      this.initCarOwner({ custType });
      // 切换客户类型时，证件类型给默认值 TUDO chenjiafeng
      if (custType == "01") {
        this.carOwner.identityType = "111";
        this.carOwner.nationality = "CHN";
        if (this.areaCode == "5201" || this.areaCode == "5301") {
          this.carOwner.gender = "1";
        }
        if (
          this.areaCode == "5201" ||
          this.areaCode == "5301" ||
          this.areaCode == "1201" ||
          this.areaCode == "3401"
        ) {
          //显示职业的地区
          this.carOwner.occupation = "900";
        }
      } else if (custType == "02") {
        this.carOwner.identityType = "810";
        this.carOwner.occupation = "";
        this.carOwner.gender = "";
      }
    },
    phoneInputFocus(e) {
      if (
        this.carOwner.phoneNo.length == 11 &&
        this.carOwner.phoneNo.includes("*")
      ) {
        this.carOwner.phoneNo = "";
        this.isPreset = false;
      }
      e.target.placeholder = "";
    },
    back() {
      if (this.control.from) {
        this.$router.push({
          name: this.control.from,
          query: {
            id: this.$route.query.orderId,
            status: this.$route.query.status
          }
        });
        this.control.from = "";
        this.initVehicle();
      } else {
        if (this.$GetRequest("vehicleType", true)) {
          this.$native.closeWeb();
        } else {
          this.$router.push({ path: "/" });
        }
      }
      console.warn("这才是车险退出啊!");
      this.$destroy();
    },
    goHome() {
      this.$router.push({ path: "/" });
    },
    input(e, type) {
      switch (type) {
        case "identityNo": {
          if (this.carOwner.identityType == "111") {
            if (this.carOwner.identityNo.length == 15) {
              let { status } = this.$validate(
                { idcard: "不符合规则" },
                this.carOwner.identityNo
              );
              if (status) {
                const { birthday, age, gender } = this.parsingIdentifyNo(
                  this.carOwner.identityNo
                );
                this.carOwner.birthday = birthday;
                this.carOwner.age = age;
                this.carOwner.gender = gender;
              }
            } else if (this.carOwner.identityNo.length == 18) {
              let { status } = this.$validate(
                { idcard: "不符合规则" },
                this.carOwner.identityNo
              );
              if (status) {
                const { birthday, age, gender } = this.parsingIdentifyNo(
                  this.carOwner.identityNo
                );
                this.carOwner.birthday = birthday;
                this.carOwner.age = age;
                this.carOwner.gender = gender;
              }
            }
          }
          break;
        }
        default:
          break;
      }
      // e.target.value = e.target.value.toUpperCase();
    },
    parsingIdentifyNo(identifyNo) {
      let year, month, date, birthday, age, gender;
      if (identifyNo.length == 15) {
        year = "19" + identifyNo.substr(6, 2);
        month = identifyNo.substr(8, 2);
        date = identifyNo.substr(10, 2);
        birthday = new Date(year, month - 1, date).format();
        age = new Date().getFullYear() - year;
        gender = identifyNo.substr(-1, 1);
        if (gender % 2) {
          //奇数为男
          gender = "1";
        } else {
          //偶数为女
          gender = "2";
        }
      } else if (identifyNo.length == 18) {
        year = identifyNo.substr(6, 4);
        month = identifyNo.substr(10, 2);
        date = identifyNo.substr(12, 2);
        birthday = new Date(year, month - 1, date).format();
        age = new Date().getFullYear() - year;
        gender = identifyNo.substr(-2, 1);
        if (gender % 2) {
          //奇数为男
          gender = "1";
        } else {
          //偶数为女
          gender = "2";
        }
      }
      return { year, month, date, birthday, age, gender };
    },
    toVehicleChannel() {
      const query = { riskCode: this.riskCode };
      const params = { confirm: this.setProjectChannel };
      this.$root.$once("channelChange", channel => {
        this.setProjectChannel(channel);
      });
      this.setChannel(this.projectChannel);
      this.$router.push({ name: "vehicleChannel", query, params });
    },
    /** 根据订单号获取车辆信息 */
    carInfoByOrderId() {
      let params = { orderId: this.orderId };
      carInfoByOrderId(params).then(
        res => {
          if (res.code === "1" && res.data) {
            this.resolveInfo(res.data);
            this.agentQuery();
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    /** 根据车牌号获取车辆信息 */
    carInfoByLicenseNo() {
      if (!this.car.licenseNo && this.car.noLicenseFlag === "0") {
        return this.$toast.fail("请输入车牌号");
      }
      let params = {
        licenseNo: this.car.licenseNo,
        carOwner: this.carOwner.name,
        licenseType: this.car.licenseType,
        noLicenseFlag: this.car.noLicenseFlag
      };
      carInfoByLicenseNo(params).then(
        res => {
          if (res.code === "1" && res.data) {
            this.resolveInfo(res.data);
            this.control.vehicleModelChecked = false;
            //埋点
            trackMap("c_car_carinfo_prefill", "车险车辆定型数据预填", {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "车险出单"
            });
          } else {
            if (!this.carOwner.name && this.control.aliCount < 20) {
              this.reSearchType = "licenseNo";
            } else if (this.control.aliCount >= 20) {
              this.$dialog.alert({
                message:
                  "车主姓名查询次数已达上限，请输入车架号或者其他信息查询车辆信息"
              });
            } else {
              this.$dialog.alert({
                message: "请输入车架号，再次查找车辆信息！"
              });
            }
          }
        },
        error => {}
      );
    },
    /** 根据车架号获取车辆信息 */
    carInfoByFrameNo() {
      if (this.localVehicle) {
        if (
          !this.car.frameNo ||
          !(this.car.frameNo.length == 6 || this.car.frameNo.length == 17)
        ) {
          return this.$toast.fail("请输入车架号");
        }
      } else {
        if (!this.car.frameNo || this.car.frameNo.length !== 17) {
          return this.$toast.fail("请输入车架号");
        }
      }
      let params = {
        licenseNo: this.car.licenseNo,
        frameNo: this.car.frameNo,
        noLicenseFlag: this.car.noLicenseFlag
      };
      carInfoByFrameNo(params).then(
        res => {
          if (res.code === "1" && res.data) {
            this.resolveInfo(res.data);
            this.control.vehicleModelChecked = false;
            // } else {
            //   if (!this.carOwner.name && this.control.aliCount < 20) {
            //     this.reSearchType = "frameNo";
            //   } else if (this.control.aliCount >= 20) {
            //     this.$dialog.alert({
            //       message: ""
            //     });
          } else {
            this.$dialog.alert({
              message: "请输入更多信息点击车型查询按钮进行查询！"
            });
          }
        },
        error => {}
      );
    },
    reSearch() {
      if (this.reSearchType == "licenseNo") {
        this.carInfoByLicenseNo();
      }
      if (this.reSearchType == "frameNo") {
        this.carInfoByFrameNo();
      }
    },
    resolveInfo(data) {
      //阿里查询次数
      let aliCount = data.aliCount || 0;
      //预填类型
      let preFillType = data.preFillType || "";
      let channel = data.vehicleChannelVO || data.channelInfo;
      //车辆信息
      let carInfo = data.vehicleModelVO || {};
      //车主信息
      let carOwner = data.vehicleOwnerVO || {};
      //投被保人信息
      let relationPeopleVoList = data.relationPeopleVoList || [];
      //历史保单险种信息
      let vehicleKindVoList = data.policyCoverageDtoList || [];
      //新增设备信息
      let newDevices = data.vehicleAccessoryDtoList || [];
      //车船税信息
      let vehicleTaxVo = data.vehicleTaxDto || {};
      //历史保单信息
      let policyInfo = data.policyDto || {};
      //是否续保标志 1-是 0-否
      let renewalFlag = data.renewalFlag || "0";
      //开票信息
      let invoiceInfo = data.invoiceInfo || null;
      let historyPolicy = null;
      carInfo.renewalFlag = renewalFlag;
      if (this.vehicleType == "1") {
        //NOTE: 家用车 使用性质和车辆种类
        if (this.riskCode == "DEA") {
          carInfo.useNatureCode = "85";
          carInfo.carKindCode = "11";
        }
        if (["01", "02"].includes(carOwner.nature)) {
          carOwner.custType = carOwner.custType || carOwner.nature;
        } else {
          carOwner.custType = carOwner.custType || "01";
        }
      } else {
        if (["01", "02"].includes(carOwner.nature)) {
          carOwner.custType = carOwner.custType || carOwner.nature;
        } else {
          carOwner.custType = carOwner.custType || "02";
        }
      }
      // 河北地区通过车牌号查询历史保单，如未返回areaCode,设置行政地区为空 TUDO chenjiafeng
      if (!carOwner.areaCode) {
        carOwner.areaDetail = "";
      }
      if (carOwner.gender == "男") {
        carOwner.gender = "1";
      } else if (carOwner.gender == "女") {
        carOwner.gender = "2";
      }
      if (/[\u4e00-\u9fa5]/.test(carOwner.nationality)) {
        carOwner.nationality =
          Object.keys(Config.nationality).find(
            key => Config.nationality[key] == carOwner.nationality
          ) || "CHN";
      }
      const { identityType, identityNo } = carOwner;
      if (identityType == "111") {
        const { birthday, age, gender } = this.parsingIdentifyNo(identityNo);
        carOwner.birthday = birthday;
        carOwner.age = age;
        carOwner.gender = gender;
      }
      if (channel && channel.agentAgreementCode) {
        //TODO 续保无agentAgreementName
      }
      if (invoiceInfo) {
        historyPolicy = Object.assign({}, historyPolicy, {
          invoice: invoiceInfo
        });
      }
      if (channel) {
        this.setProjectChannel(channel);
      }
      //从订单过来，如果勾选未上牌，号牌类型置空(除深圳) TUDO chenjiafeng
      if (carInfo.noLicenseFlag == "1" && this.areaCode != "4403") {
        this.car.licenseType = "";
      }
      // 订单过来的摩托车，号牌类型都默认24 TUDO chenjiafeng
      if (["51", "52", "53"].includes(carInfo.carKindCode)) {
        carInfo.licenseType = "24";
        carInfo.useNatureCode = "000";
        if (carInfo.noLicenseFlag == "1") {
          carInfo.licenseType = "";
        }
      }
      this.setCarInfo(this.$utils.removeEmpty(carInfo));
      this.setCarOwner(this.$utils.removeEmpty(carOwner));
      if (Array.isArray(relationPeopleVoList) && relationPeopleVoList.length) {
        let policyHolder = relationPeopleVoList.find(
          p => p.relationPeopleType == "1"
        );
        let recognizee = relationPeopleVoList.find(
          p => p.relationPeopleType == "2"
        );
        if (policyHolder) {
          let relationFlag =
            policyHolder.name == this.carOwner.name ? "1" : "0";
          let vehicleRelation =
            policyHolder.vehicleRelation ||
            (policyHolder.name == this.carOwner.name ? "1" : "2");
          let custType = ["01", "02"].includes(policyHolder.insuredNature)
            ? policyHolder.insuredNature
            : policyHolder.insuredNature == "3"
            ? "01"
            : policyHolder.insuredNature == "4"
            ? "02"
            : "01";
          let presetPolicyHolder = Object.assign(
            {},
            RelationPeople,
            policyHolder,
            {
              custType,
              vehicleRelation,
              relationFlag
            }
          );
          this.setPolicyHolder(presetPolicyHolder);
          historyPolicy = Object.assign({}, historyPolicy, {
            policyHolder: presetPolicyHolder
          });
        }
        if (recognizee) {
          let relationFlag =
            recognizee.name == policyHolder.name
              ? "2"
              : recognizee.name == this.carOwner.name
              ? "3"
              : "0";
          let vehicleRelation =
            recognizee.vehicleRelation ||
            (recognizee.name == policyHolder.name
              ? this.policyHolder.vehicleRelation
              : "2");
          let custType = ["01", "02"].includes(recognizee.insuredNature)
            ? recognizee.insuredNature
            : recognizee.insuredNature == "3"
            ? "01"
            : recognizee.insuredNature == "4"
            ? "02"
            : "01";
          let presetRecognizee = Object.assign({}, RelationPeople, recognizee, {
            custType,
            relationFlag,
            vehicleRelation
          });
          this.setRecognizee(presetRecognizee);
          historyPolicy = Object.assign({}, historyPolicy, {
            recognizee: presetRecognizee
          });
        }
      }
      if (Array.isArray(vehicleKindVoList) && vehicleKindVoList.length) {
        //交强险险别
        let trafficInfo = vehicleKindVoList.find(
          item => item.productElementCode == "C100120"
        );
        if (trafficInfo) {
          historyPolicy = Object.assign({}, historyPolicy, {
            tax: vehicleTaxVo,
            trafficChecked: "1", //是否勾选交强险
            trafficEffectiveEndDate: trafficInfo.expiryDate, //交强险终保日期
            trafficEffectiveStartDate: trafficInfo.effectiveDate, //交强险起保日期
            trafficBeforeVatPremium: "", //交强险税前真实保费,不含税保费
            trafficDuePremium: trafficInfo.duePremium, //交强险应收保费
            trafficStdPremium: trafficInfo.stdPremium, //交强险标准保费
            trafficSumInsured: trafficInfo.sumInsured, //交强险保额
            trafficPremium: trafficInfo.deductiblePremium //交强险保费
          });
        }
        //商业险险别
        let businessInfo = vehicleKindVoList.filter(
          item =>
            Config.kindCode.hasOwnProperty(item.productElementCode) &&
            item.productElementCode != "C100120"
        );
        if (businessInfo.length) {
          let businessChecked = "1";
          let businessEffectiveEndDate = businessInfo[0].expiryDate;
          let businessEffectiveStartDate = businessInfo[0].effectiveDate;
          let businessRisks = businessInfo.map(risk => {
            let extension = risk.policyCoverageExtension || {};
            let kindCode = risk.productElementCode;
            //NOTE:
            //absDeductDuePremium 绝对免赔率特约险应收保费
            //??? 小盆友, 你是不是有很多问号 ？??? 居然发现一个新的bug
            return Object.assign({}, Risk, {
              kindCode: risk.productElementCode, //险种代码
              kindName: risk.coverageName, //险种名称
              amount: risk.sumInsured, //保额
              brokenCode: extension.glassType || extension.repairFactory || "", //玻璃破碎险：玻璃类型, 指定修理厂险类型
              deductibleRate:
                kindCode == "C100135"
                  ? risk.premiumRate
                  : risk.absDeductibleCode || "", //浮动比例/绝对免赔率
              deductibleValue: extension.deductible || "", //自负额
              premium: risk.duePremium || "", //保费
              quantity:
                kindCode == "C100124"
                  ? this.car.seatCount - 1
                  : extension.passagerSeatNumber || extension.payDays || "", //投保座位数/投保天数
              kindFlag: "1", //risk.isNonDeductible ? "1" : "0", //不计免赔标志(1:不计免赔 0:计免赔)
              unitAmount:
                kindCode == "C100124"
                  ? (risk.sumInsured / (this.car.seatCount - 1)).toFixed(2)
                  : extension.avgSumInsured || extension.dayPayInsured || "", //每座保额/每天保额
              rate: risk.shortRate || "", //费率
              nonDeductiblePremium: risk.deductiblePremium || "", //不计免赔险实收保费
              nonDeductiblePremiumRate: risk.deductiblePremiumRate || "", //不计免赔险费率
              nonDeductibleStandardPremium: risk.deductibleStdPremium || "", //不计免赔险标准保费
              standardPremium: risk.stdPremium || "" //标准保费
            });
          });
          historyPolicy = Object.assign({}, historyPolicy, {
            businessChecked,
            businessEffectiveEndDate,
            businessEffectiveStartDate,
            businessIsEffective: "0",
            businessRisks,
            newDevices
          });
        }
      }
      if (policyInfo.productCode && typeof policyInfo.productCode == "string") {
        if (/Y/i.test(policyInfo.isEffectiveImmediately)) {
          policyInfo.isEffectiveImmediately = "1";
        } else if (/N/i.test(policyInfo.isEffectiveImmediately)) {
          policyInfo.isEffectiveImmediately = "0";
        }
        if (policyInfo.productCode.indexOf("DF") != -1) {
          //交强险
          historyPolicy = Object.assign({}, historyPolicy, {
            trafficChecked: "1", //是否勾选交强险
            trafficIsEffective: "0"
          });
        } else if (policyInfo.productCode.indexOf("DE") != -1) {
          //商业险
          historyPolicy = Object.assign({}, historyPolicy, {
            businessChecked: "1", //是否勾选交强险
            businessIsEffective: "0"
          });
        }
      }
      this.setHistoryPolicy(
        historyPolicy ? Object.assign({}, Plan, historyPolicy) : null
      );
      this.car.licenseNo = carInfo.licenseNo;
      if (carOwner.phoneNo && carOwner.phoneNo.length == 11) {
        this.isPreset = true;
      }
      this.control.aliCount = aliCount;
    },
    saveToOrder() {
      //存vehicleType入库 TUDO chenjiafeng
      this.car.vehicleTypeFlag = this.vehicleType;
      let params = {
        systemSource: "1",
        orderId: this.orderId,
        vehicleChannelVO: this.projectChannel,
        vehicleModelVO: this.car,
        vehicleOwnerVO: this.carOwner,
        renewalFlag: this.car.renewalFlag
      };
      saveToOrder(params).then(
        res => {
          if (res.code === "1") {
            this.setOrderId(res.data.orderId);
            this.getTaxConfig();
            this.$router.push({ name: "insurancePlans" });
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    selectCarModel() {
      if (this.car.noLicenseFlag == "1") {
        if (!this.car.frameNo || !this.car.engineNo) {
          this.$toast("请输入车架号或发动机号");
          return;
        }
      } else {
        if (!this.car.licenseNo || !this.car.frameNo || !this.car.engineNo) {
          this.$toast("请输入车牌号/车架号或发动机号");
          return;
        }
      }
      if (this.car.frameNo.length !== 17) {
        this.$toast("车架号不足17位");
      }
      this.$router.push({ name: "vehicleModels" });
    },
    changeDatePickerType(type) {
      let now = new Date();
      let value = this.car[type] || this.carOwner[type];
      let currentDate = value ? new Date(value.replace(/-/g, "/")) : new Date();
      this.currentDate = this.isValidDate(currentDate) ? currentDate : now;
      switch (type) {
        case "birthday": {
          let defaultDate = new Date();
          defaultDate.setFullYear(defaultDate.getFullYear() - 18);
          if (!this.carOwner[type]) {
            this.currentDate = defaultDate;
          }
          this.minDate = new Date(1900, 0, 1);
          this.maxDate = now;
          break;
        }
        case "enrollDate": {
          this.minDate = new Date(1900, 0, 1);
          this.maxDate = now;
          break;
        }
        case "transferDate": {
          if (this.enrollDate) {
            this.minDate = new Date(this.enrollDate.replace(/-/g, "/"));
          } else {
            this.minDate = new Date(2000, 0, 1);
          }
          this.maxDate = now;
          break;
        }
        case "identifyStartDate": {
          this.minDate = new Date(1949, 0, 1);
          this.maxDate = new Date();
          break;
        }
        case "identifyEndDate": {
          this.minDate = new Date();
          this.maxDate = new Date(2100, 0, 1);
          break;
        }
        case "carInvoiceDate": // 购车发票日期
        case "firstyearDate": // 首年投保日期
        case "certificateDate": // 车辆来历凭证所载日期
        case "travelIssueDate": {
          // 行驶证发证日期
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          break;
        }
        default: {
          new Date(1900, 0, 1);
          break;
        }
      }
      this.datePickerType = type;
    },
    onDatePickerConfirm(date) {
      switch (this.datePickerType) {
        case "birthday": {
          this.carOwner.birthday = date.format();
          this.carOwner.age = new Date().getFullYear() - date.getFullYear();
          break;
        }
        case "enrollDate": {
          this.car.enrollDate = date.format("yyyy-MM-dd 00:00:00");
          this.control.vehicleModelChecked = false;
          let check = new Date();
          check.setMonth(check.getMonth() - 9);
          if (date.getTime() < check.getTime()) {
            // this.car.newVehicle = "1";
          }
          break;
        }
        case "transferDate": {
          this.car.transferDate = date.format();
          break;
        }
        case "identifyStartDate":
          this.carOwner.identifyStartDate = date.format("yyyy-MM-dd");
          break;
        case "identifyEndDate": {
          this.carOwner.identifyEndDate = date.format("yyyy-MM-dd");
          break;
        }
        case "carInvoiceDate": // 购车发票日期
        case "firstyearDate": // 首年投保日期
        case "certificateDate": // 车辆来历凭证所载日期
        case "travelIssueDate": {
          // 行驶证发证日期
          this.car[this.datePickerType] = date.format("yyyy-MM-dd");
          break;
        }
        default:
          break;
      }
      this.datePickerType = "";
    },

    setPicker(dataPickerType) {
      let orderObject = this.carOwner;
      switch (dataPickerType) {
        case "salesChannel": {
          orderObject = this.car;
          this.pickerData = this.salesChannels;
          break;
        }
        case "certificateType": {
          orderObject = this.car;
          this.pickerData = this.certificateTypes;
          break;
        }
        case "restricFlag": {
          orderObject = this.car;
          this.pickerData = this.restricFlags;
          break;
        }
        case "identifyStartDate": {
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          this.datePickerType = dataPickerType;
          break;
        }
        case "identifyEndDate": {
          this.minDate = new Date();
          this.maxDate = new Date(2100, 11, 31);
          this.datePickerType = dataPickerType;
          break;
        }
        case "identityType": {
          this.pickerData = this.identityTypes;
          break;
        }
        case "occupation": {
          this.pickerData = this.occupations;
          break;
        }
        case "nationality": {
          this.pickerData = this.nationalitys;
          break;
        }
        case "carKindCode": {
          orderObject = this.car;
          this.pickerData = this.carKindCodes;
          break;
        }
        case "useNatureCode": {
          orderObject = this.car;
          this.pickerData = this.useNatureCodes;
          break;
        }
        case "licenseType": {
          orderObject = this.car;
          this.pickerData = this.licenseTypes;
          break;
        }
        case "vehicleCategory": {
          orderObject = this.car;
          this.pickerData = this.vehicleCategorys;
          break;
        }
        default:
          break;
      }
      this.pickerData = [{ text: "请选择", value: "" }, ...this.pickerData];
      this.defaultIndex = this.pickerData.findIndex(
        item => item.value == _.result(orderObject, dataPickerType, "")
      );
      this.dataPickerType = dataPickerType;
    },
    dataPickerConfirm(data) {
      switch (this.dataPickerType) {
        case "salesChannel":
        case "certificateType":
        case "licenseType": {
          this.car[this.dataPickerType] = data.value;
          let licenseType = this.car.licenseType;
          switch (licenseType) {
            case "02":
            case "18":
              this.car.licenseColorCode = "1";
              break;
            case "03":
            case "04":
            case "05":
            case "06":
              this.car.licenseColorCode = "2";
              break;
            case "20":
            case "22":
            case "23":
            case "24":
            case "32":
              this.car.licenseColorCode = "3";
              break;
            case "01":
            case "13":
              this.car.licenseColorCode = "4";
              break;
            case "52":
              this.car.licenseColorCode = "6";
              break;
            case "51":
              this.car.licenseColorCode = "7";
              break;
            case "99":
              this.car.licenseColorCode = "8";
              break;
            default:
              break;
          }
          //广东地区号牌类型、初登日期、车架号、厂牌型号、未上牌改为已上牌修改需要重新定型
          if (this.areaCode == "4401") {
            this.control.vehicleModelChecked = false;
          }
          break;
        }
        case "restricFlag": {
          this.car[this.dataPickerType] = data.value;
          break;
        }
        case "carKindCode": {
          this.car[this.dataPickerType] = data.value;
          console.log("carKindCode" + this.car.carKindCode);
          let carKindCode = this.car.carKindCode;
          if (
            [
              "51",
              "52",
              "53",
              "61",
              "62",
              "63",
              "64",
              "9A",
              "9B",
              "AA",
              "AB",
              "AC",
              "AD",
              "AE"
            ].includes(carKindCode)
          ) {
            //车辆种类为【摩托车、拖拉机】的交管车辆类型默认【M21-普通二轮摩托车】
            this.car.vehicleCategory = "M21";
          }
          if (
            carKindCode == "21" ||
            carKindCode == "22" ||
            carKindCode == "31"
          ) {
            //车辆种类为【货车、低速载货汽车、挂车】的交管车辆类型无默认值
            this.car.useNatureCode = "";
            this.car.vehicleCategory = "";
          }
          if (carKindCode == "11") {
            this.car.useNatureCode = "220";
            this.car.vehicleCategory = "K31";
          }
          if (
            //车辆种类为【摩托车、拖拉机】时显示本字段，枚举值为全量枚举值，默认为【黄】
            carKindCode == "61" ||
            carKindCode == "62" ||
            carKindCode == "63" ||
            carKindCode == "64"
          ) {
            this.car.licenseColorCode = "4";
            //拖拉机--默认号牌类型为14-拖拉机号牌，不显示
            this.car.licenseType =
              this.car.noLicenseFlag == "1" && this.areaCode != "4403"
                ? ""
                : "14";
          }
          // 摩托车--上海、广东--不显示号牌底色
          if (
            carKindCode == "51" ||
            carKindCode == "52" ||
            carKindCode == "53"
          ) {
            if (this.areaCode == "4401" || this.areaCode == "3101") {
              this.car.licenseColorCode = "";
              //摩托车--上海、广东--默认号牌种类为“”
              this.car.licenseType = this.car.noLicenseFlag == "1" ? "" : "24";
            } else {
              this.car.licenseColorCode = "4";
              // 摩托车--全国除了上海、广东--默认号牌种类--24-警用摩托车号牌/公安民用号牌
              this.car.licenseType = this.car.noLicenseFlag == "1" ? "" : "24";
            }
          }
          break;
        }
        case "useNatureCode": {
          this.car[this.dataPickerType] = data.value;
          console.log("useNatureCode" + this.car.useNatureCode);
          let useNatureCode = this.car.useNatureCode;
          if (
            this.car.carKindCode == "11" &&
            !["85", "102", "103", "105"].includes(useNatureCode)
          ) {
            //车辆种类为【客车】且车辆使用性质非【家庭自用】102、103、105的交管车辆类型默认【K31-小型普通客车】
            this.car.vehicleCategory = "K31";
          }
          // 102、103、105 默认K11
          if (
            this.car.carKindCode == "11" &&
            ["102", "103", "105"].includes(useNatureCode)
          ) {
            this.car.vehicleCategory = "K11";
          }
          if (this.car.carKindCode == "11" && useNatureCode == "85") {
            this.car.vehicleCategory = "K33";
          }
          break;
        }
        case "vehicleCategory": {
          this.car[this.dataPickerType] = data.value;
          break;
        }
        default: {
          this.carOwner[this.dataPickerType] = data.value;
          break;
        }
      }
      this.dataPickerType = "";
      this.pickerData = [];
    },
    dataPickerCancel() {
      this.dataPickerType = "";
      this.pickerData = [];
    },
    OCR(type) {
      switch (type) {
        case "carOwner": {
          //调用接口参数，
          //identifyType "0" - 识别行驶证; "1" - 识别身份证
          //side "face":正面 "back":反面
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              /**
               * 姓名      name      String    N
               * 身份号码  cardNo    String    N
               * 地址      address   String    N
               * 性别      sex       String    N
               * 出生日期  birthday  String    N
               * 民族      folk      String    N
               * 正反面    side      String    N
               */
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                let carOwner = {
                  name: data.name,
                  identityNo: data.cardNo,
                  address: data.address,
                  gender: data.sex,
                  birthday: new Date(data.birthday).format(),
                  folk: data.folk
                };
                let { status } = this.$validate(
                  { idcard: "不符合规则" },
                  this.carOwner.identityNo
                );
                if (status) {
                  const { birthday, age, gender } = this.parsingIdentifyNo(
                    carOwner.identityNo
                  );
                  carOwner.birthday = birthday;
                  carOwner.age = age;
                  carOwner.gender = gender;
                }
                this.setCarOwner(carOwner);
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "drivingLicense": {
          //调用接口参数，
          //identifyType "0" - 识别行驶证; "1" - 识别身份证
          //side "face":正面 "back":反面
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.identifyOCR(
            { identifyType: "0", side: "face" },
            data => {
              /**
               *姓名                  name                String
               *姓名位置图片          namePoints          Base64
               *号牌号码              cardNo              String
               *号牌号码位置图片      cardNoPoints        Base64
               *住址                  address             String
               *住址位置图片          addressPoints       Base64
               *发证日期              issueDate           String
               *发证日期位置图片      issueDatePoints     Base64
               *车辆类型              vehicleType         String
               *车辆类型位置图片      vehicleTypePoints   Base64
               *使用性质              useCharacte         String
               *使用性质位置图片      useCharactePoints   Base64
               *品牌型号              model               String
               *品牌型号位置图片      modelPoints         Base64
               *车辆识别代号          vin                 String
               *车辆识别代号位置图片  vinPoints           Base64
               *车辆识别代号          enginePN            String
               *车辆识别代号位置图片  enginePNPoints      Base64
               *注册日期              registerDate        String
               *注册日期位置图片      registerDatePoints  Base64
               *正反面                side                String
               */
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                // @suncao 车辆使用性质以OCR为主
                // let useNatureCode = Object.keys(Config.useNatureCode).find(
                //   key => Config.useNatureCode[key] == data.useCharacte
                // );
                this.carOwner.name = data.name;
                this.setCarInfo({
                  licenseNo: data.cardNo,
                  vehicleType: data.vehicleType,
                  // useNatureCode: useNatureCode || this.car.useNatureCode,
                  useNatureName: data.useCharacte,
                  vehicleBrand: data.model,
                  frameNo: data.vin,
                  engineNo: data.enginePN,
                  enrollDate: data.registerDate
                    ? new Date(data.registerDate).format("yyyy-MM-dd 00:00:00")
                    : ""
                });
                this.control.licenseNoImage = data.cardNoPoints
                  ? "data:image/png;base64," + data.cardNoPoints
                  : "";
                this.control.frameNoImage = data.vinPoints
                  ? "data:image/png;base64," + data.vinPoints
                  : "";
                this.control.enrollDateImage = data.registerDatePoints
                  ? "data:image/png;base64," + data.registerDatePoints
                  : "";
                this.control.engineNoImage = data.enginePNPoints
                  ? "data:image/png;base64," + data.enginePNPoints
                  : "";
                this.control.vehicleBrandImage = data.modelPoints
                  ? "data:image/png;base64," + data.modelPoints
                  : "";
                //埋点
                trackMap("c_car_carinfo_ocr", "车险车辆定型OCR识别", {
                  c_salesperson_type: "N",
                  c_market_type: "Y",
                  c_market_name: "",
                  c_market_id: "",
                  c_event_category: "车险出单"
                });
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        default:
          break;
      }
    },
    saveVehicleInfo() {
      // 校验数据,提交数据
      if (this.validate()) {
        // 保存车辆信息,获取订单号
        this.saveToOrder();
        trackMap("c_car_carinfo_next", "车险车辆定型下一步", {
          c_salesperson_type: "N",
          c_market_type: "Y",
          c_market_name: "",
          c_market_id: "",
          c_event_category: "车险出单"
        });
      }
    },
    validate() {
      const { isProxy } = this.projectChannel;
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      let areaCode = comCode.substr(0, 4);
      if (!this.control.vehicleModelChecked) {
        this.$dialog.alert({ message: "请重新进行车型查询" }).then(() => {
          this.selectCarModel();
        });
        return false;
      }
      let props = JSON.parse(JSON.stringify(ValidateRules));
      if (this.car.noLicenseFlag != "1") {
        props["car.licenseNo"] = {
          required: "车牌号",
          licenseNo2: "不符合规则"
        };
        //上海广东、摩托车--号牌类型必填
        // if (this.areaCode == "4401" && this.areaCode == "3101") {
        //   props["car.licenseType"] = { required: "号牌类型" };
        // } else {
        //   // 其他地区、摩托车--号牌类型不必填
        //   if (
        //     this.car.carKindCode !== "51" &&
        //     this.car.carKindCode !== "52" &&
        //     this.car.carKindCode !== "53"
        //   ) {
        //     props["car.licenseType"] = { required: "号牌类型" };
        //   }
        // }
      } else if (this.car.licenseType && this.areaCode != "4403") {
        this.car.licenseType = "";
        // this.$dialog.alert({ message: "未上牌时不允许录入号牌类型" });
        // return false;
      }
      if (this.car.chgOwnerFlag === "1") {
        props["car.transferDate"] = { required: "转登日期" };
      }
      if (this.areaCode == "3101" || this.areaCode == "3109") {
        props["car.salesChannel"] = { required: "销售渠道" };
      }
      if (this.areaCode == "1101") {
        props["car.carRegiste"] = { required: "是否新车备案" };
        if (this.car.carRegiste == "1") {
          props["car.carRegiste"] = { required: "车辆来历凭证编号" };
          props["car.certificateType"] = { required: "车辆来历凭证种类" };
          props["car.certificateDate"] = { required: "车辆来历凭证所载日期" };
        }
        if (this.car.ecdemicVehicle == "1") {
          props["car.travelIssueDate"] = { required: "行驶证发证日期" };
        }
      }
      if (this.carOwner.custType == "01") {
        //个人
        //北京 特有
        if (areaCode == "1101") {
          props["carOwner.birthday"] = { required: "出生日期" };
        }
        //云南 贵州 特有
        if (areaCode == "5201" || areaCode == "5301") {
          props["carOwner.gender"] = { required: "性别" };
        }
        //云南 贵州 天津 安徽 特有
        if (
          areaCode == "5201" ||
          areaCode == "5301" ||
          areaCode == "1201" ||
          areaCode == "3401"
        ) {
          props["carOwner.nationality"] = { required: "国籍" };
          props["carOwner.occupation"] = { required: "职业" };
        }
        //贵州 特有
        if (areaCode == "5201") {
          props["carOwner.address"] = { required: "联系地址" };
        }
        if (areaCode == "5201" && this.comCName.indexOf("安顺中") !== -1) {
          props["carOwner.identifyStartDate"] = { required: "证件有效起期" };
          props["carOwner.identifyEndDate"] = { required: "证件有效止期" };
          props["carOwner.gender"] = { required: "性别" };
        }
        if (
          this.$utils.findText(
            this.identityTypes,
            this.carOwner.identityType
          ) == "请选择"
        ) {
          this.$toast.fail("不支持的证件类型, 请重新选择证件类型");
          return false;
        }
        if (this.carOwner.identityType === "111") {
          //TODO: 港澳台通行证暂不处理
          //|| this.carOwner.identityType === "28";
          props["carOwner.identityNo"].idcard = "不符合规则";
        }
        if (this.carOwner.identityType === "553") {
          //外国人永久居留证
          props["carOwner.identityNo"].required = "外国人永久居留证";
          props["carOwner.identityNo"].length = {
            lenght: 15,
            msg: "证件号码长度为15位"
          };
        }
        if (this.carOwner.identityType === "03") {
          //TODO: 护照校验暂不处理
          // props["carOwner.identityNo"].passport = "不符合规则";;
        }
      } else if (this.carOwner.custType == "02") {
        //单位
        props["carOwner.identityNo"].companyId = "不符合规则";
      }
      if (this.carOwner.email) {
        props["carOwner.email"] = { email: "邮箱地址不符合规则" };
      }
      if (this.car.carCheckStatus == "1") {
        props["car.carChecker"] = { required: "验车人" };
        props["car.carCheckTime"] = { required: "验车时间" };
      }
      // 非家用车 车辆种类 TUDO chenjiafeng
      if (this.vehicleType !== "1") {
        props["car.carKindCode"] = { required: "车辆种类" };
      }
      // 非家用车 车辆使用性质 TUDO chenjiafeng
      if (this.vehicleType == "2" || this.vehicleType == "4") {
        props["car.useNatureCode"] = { required: "车辆使用性质" };
      }
      // 非家用车为货车时，整备质量、核定载质量 TUDO chenjiafeng
      if (this.car.carKindCode == "21") {
        props["car.net"] = { required: "整备质量" };
        props["car.tonCount"] = { required: "核定载质量" };
      }
      //非家用车为挂车时，牵引总质量 TUDO chenjiafeng
      if (this.car.carKindCode == "31") {
        props["car.haulage"] = { required: "牵引总质量" };
      }
      // 上海地区不显示交管车辆类型 TUDO chenjiafeng
      if (this.areaCode !== "3101") {
        props["car.vehicleCategory"] = { required: "交管车辆类型" };
      }
      //拖拉机地区费率分类----安徽、广西、湖北、湖南、贵州、四川地区--车辆种类选择：运输型拖拉机、兼用型拖拉机 时显示且必填 TUDO chenjiafeng
      if (
        this.areaCode == "3401" ||
        this.areaCode == "4501" ||
        this.areaCode == "4201" ||
        this.areaCode == "4301" ||
        this.areaCode == "5201" ||
        this.areaCode == "5101"
      ) {
        if (
          this.car.carKindCode == "61" ||
          this.car.carKindCode == "62" ||
          this.car.carKindCode == "63" ||
          this.car.carKindCode == "64"
        ) {
          props["car.rateClassification"] = { required: "拖拉机地区费率分类" };
        }
      }
      //当车辆使用性质选择为【营业城市公交】【营业公路客运】时，核定载客数不能小于6 TUDO chenjiafeng
      if (this.car.useNatureCode == "102" || this.car.useNatureCode == "103") {
        if (this.car.seatCount < 6) {
          this.$dialog.alert({ message: "核定载客数不能小于6" });
          return false;
        }
      }
      // 排量：车辆种类为【客车、摩托车】时显示，且必填
      if (["11", "51", "52", "53"].includes(this.car.carKindCode)) {
        props["car.exhaustScale"] = { required: "排量" };
      }
      // 功率：车辆种类为【拖拉机】时显示，必填
      // 如地区有“拖拉机地区费率分类”（安徽、广西、湖北、湖南、贵州、四川地区），
      // 并且车辆种类选择“运输拖拉机”或“兼用拖拉机”，则该地区录单页面上车辆信息模块的“功率、排量”字段均不显示
      if (
        !["3401", "4501", "4201", "4301", "5201", "5101"].includes(
          this.areaCode
        ) &&
        ["61", "62", "63", "64"].includes(this.car.carKindCode)
      ) {
        props["car.powerScale"] = { required: "功率" };
      }
      // NOTE: 渠道校验使用的数据 -_-! 哇, 真是太过分了
      let salesInfoArea = false;
      if (
        areaCode == "4401" ||
        areaCode == "6201" ||
        areaCode == "4403" ||
        areaCode == "6101"
      ) {
        salesInfoArea = true;
      }
      let salesInfoRequired = false;
      //业务来源为专业代理、兼业代理、经纪业务、个人代理
      let v =
        this.projectChannel.businessNature ||
        this.projectChannel.businessNatureCode;
      if (
        v == "2101" ||
        v == "2205" ||
        v == "6700" ||
        v == "2105" ||
        v == "2102" ||
        v == "2401"
      ) {
        salesInfoRequired = true;
      }
      //陕西 NB - -!!!
      //必定校验
      if (areaCode == "6101") {
        props["projectChannel.salesCodeName"] = { required: "销售机构名称" };
        props["projectChannel.mobile"] = {
          required: "联系电话",
          phoneormobile: "不符合规则"
        };
        props["projectChannel.salesName"] = { required: "销售人员姓名" };
        if (v == "2401") {
          props["projectChannel.exhibitionNo"] = { required: "执业证书编号" };
        } else {
          props["projectChannel.permitNo"] = { required: "许可证号" };
        }
      }
      //青海 内蒙古
      if (areaCode == "1501" || areaCode == "6301") {
        props["projectChannel.operatorPhone"] = { required: "归属经办人电话" };
      }
      if (isProxy === "0") {
        //不代理
      } else if (isProxy === "1") {
        //代理
        props["projectChannel.agentCode"] = { required: "代理人" };
        props["projectChannel.agentAgreementCode"] = { required: "代理人协议" };
        if (salesInfoArea && salesInfoRequired) {
          props["projectChannel.salesName"] = { required: "销售人员姓名" };
          if (v == "2401") {
            props["projectChannel.exhibitionNo"] = { required: "执业证书编号" };
          } else {
            props["projectChannel.permitNo"] = { required: "许可证号" };
          }
        }
        //深圳
        if (areaCode == "4403") {
          props["projectChannel.salesIdNo"] = { required: "销售人员身份证号" };
        }
        //江苏/浙江
        if ((areaCode == "3201" || areaCode == "3301") && v != "2401") {
          props["projectChannel.agencyOrgCode"] = {
            required: "中介机构代码"
          };
        }
      }
      let thirdPartyRequired = false;
      if (v && typeof v === "string") {
        if (
          v.indexOf("030") == 0 ||
          v.indexOf("220") == 0 ||
          v.indexOf("230") == 0 ||
          v.indexOf("250") == 0 ||
          v.indexOf("260") == 0
        ) {
          thirdPartyRequired = true;
        }
      }
      //福建
      if (areaCode == "3501") {
        props["projectChannel.salesName"] = { required: "销售人员姓名" };
        if (thirdPartyRequired) {
          props["projectChannel.thirdPartyHandlerName"] = {
            required: "第三方业务员姓名"
          };
          props["projectChannel.thirdPartyHandlerNo"] = {
            required: "第三方业务员工号"
          };
        }
      }
      //新车且有车牌号时，功率不能为0
      if (
        this.car.noLicenseFlag == "0" &&
        this.car.licenseNo &&
        this.car.useMonth < 9
      ) {
        if (
          ["11", "21", "22", "31", "61", "62", "63", "64"].includes(
            this.car.carKindCode
          ) ||
          this.vehicleType == "4"
        ) {
          if (this.car.powerScale == "0") {
            this.$toast.fail("新车功率不能为0");
            return false;
          }
        }
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            _.result(this, prop, "")
          );
          if (!status) {
            console.error(prop, status, msg);
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(error);
        return false;
      }
      return true;
    },
    areaConfirm(res) {
      if (
        //判断是港澳台就直接拿港澳台的码值
        res[0].code == "710000" ||
        res[0].code == "810000" ||
        res[0].code == "820000"
      ) {
        this.carOwner.areaCode = res[0].code;
        this.carOwner.areaDetail = res[0].name;
      } else {
        // 其他都拿地区码值
        this.carOwner.areaCode = res[2].code;
        this.carOwner.areaDetail =
          res[0].name + " " + res[1].name + " " + res[2].name;
      }
      this.areaListShow = false;
    }
  }
};
</script>
<style lang="less" scoped>
// * {
//   font-family: ;
// }
.vehicle-title {
  position: absolute;
  top: 114px;
  left: 17px;
  height: 22px;
  line-height: 22px;
  font-size: 16px;
  font-weight: 500;
  color: black;
}
.next-button-container {
  padding-top: 20px;
  background: #f7f7f7;
  height: 61px;
  .next-button {
    flex: 1;
    margin-left: 15px;
    margin-right: 16px;
  }
}
.page-popup {
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 0;
}
.content {
  width: 100%;
  height: calc(100% - 146px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  /deep/.van-cell__value {
    overflow: visible;
  }
  .customer-group {
    flex-direction: column;
  }
  .group-content {
    flex-direction: column;
  }
  .group {
    height: 48px;
    background: #f7f7f7;
    .group-title {
      font-size: 14px;
      color: #333333;
      font-weight: 600;
      flex-direction: row;
      align-items: center;
      img {
        width: 22px;
        height: 22px;
        margin-right: 8px;
      }
    }
    .group-right {
      flex: 1;
      justify-content: flex-end;
      align-items: center;
    }
    .group-right-text {
      margin-right: 10px;
      font-size: 14px;
      font-weight: 400;
      color: #3b8afe;
    }
  }
  .group::after {
    border: 0;
  }
  .item-container {
    position: relative;
    flex-direction: column;
    background: white;
    .item-ocr-image {
      display: flex;
      align-self: flex-end;
      justify-self: flex-end;
      height: 27px;
      margin-top: 13px;
      margin-right: 15px;
      border: 1px solid;
    }
  }
  .item-container:not(:last-child)::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 16px;
    border-bottom: 1px solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
  .item-button {
    height: 40px;
    background: white;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 14px;
    color: #4c76b5;
  }
  .item {
    min-height: 48px;
    align-items: center;
    /deep/.van-cell__value {
      flex: 2;
    }
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-right {
      flex: 1;
      flex-direction: row;
      // word-break: keep-all;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: #333333;
      overflow: hidden;
      .item-right-input {
        width: 100%;
        position: relative;
        text-align: right;
        border: 0;
        background: transparent;
        opacity: 1;
        font-size: 14px;
        color: #333333;
      }
      .item-right-text {
        margin-right: 8px;
      }
      .license-no-input {
        position: relative;
        width: 90px;
        text-align: left;
        border: 0;
        margin-right: 16px;
      }
      .license-no-input-container {
        position: relative;
      }
      .license-no-input-container::after {
        position: absolute;
        box-sizing: border-box;
        content: " ";
        pointer-events: none;
        top: 3px;
        right: 0;
        bottom: 3px;
        border-right: 2px solid #ededed;
        -webkit-transform: scaleX(0.5);
        transform: scaleX(0.5);
      }
      .check-box {
        color: #333333;
        margin-left: 13px;
        overflow: visible;
        display: flex;
        flex-direction: row;
        align-items: center;
      }
      .check-box-title {
        padding-top: 2px;
        line-height: 0;
      }
      .search-image {
        width: 14px;
        height: 14px;
        margin-left: 16px;
      }
      .item-right-button {
        align-items: center;
        pointer-events: auto;
      }
      .item-right-button-text {
        color: #3b8afe;
        font-size: 14px;
        margin-left: 16px;
      }
      .item-right-button-icon {
        margin-left: 8px;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
      .item-placeholder {
        color: #ababab;
      }
    }
  }
  .short-item {
    /deep/.van-cell__value {
      flex: 1;
    }
    /deep/.van-field__value {
      flex: 1;
    }
    /deep/.van-field__label {
      width: auto;
    }
  }
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
