package info.nightscout.androidaps.dependencyInjection

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import info.nightscout.androidaps.MainApp
import info.nightscout.androidaps.data.Profile
import info.nightscout.androidaps.data.ProfileStore
import info.nightscout.androidaps.data.PumpEnactResult
import info.nightscout.androidaps.db.BgReading
import info.nightscout.androidaps.plugins.aps.loop.APSResult
import info.nightscout.androidaps.plugins.aps.openAPSAMA.DetermineBasalResultAMA
import info.nightscout.androidaps.plugins.aps.openAPSMA.DetermineBasalResultMA
import info.nightscout.androidaps.plugins.aps.openAPSMA.LoggerCallback
import info.nightscout.androidaps.plugins.aps.openAPSSMB.DetermineBasalAdapterSMBJS
import info.nightscout.androidaps.plugins.aps.openAPSSMB.DetermineBasalResultSMB
import info.nightscout.androidaps.plugins.constraints.objectives.objectives.*
import info.nightscout.androidaps.plugins.general.automation.AutomationEvent
import info.nightscout.androidaps.plugins.general.automation.actions.*
import info.nightscout.androidaps.plugins.general.automation.elements.*
import info.nightscout.androidaps.plugins.general.automation.triggers.*
import info.nightscout.androidaps.plugins.general.overview.notifications.NotificationWithAction
import info.nightscout.androidaps.plugins.general.smsCommunicator.AuthRequest
import info.nightscout.androidaps.plugins.iob.iobCobCalculator.AutosensData
import info.nightscout.androidaps.plugins.iob.iobCobCalculator.GlucoseStatus
import info.nightscout.androidaps.plugins.iob.iobCobCalculator.IobCobOref1Thread
import info.nightscout.androidaps.plugins.iob.iobCobCalculator.IobCobThread
import info.nightscout.androidaps.plugins.treatments.Treatment
import info.nightscout.androidaps.queue.CommandQueue
import info.nightscout.androidaps.queue.commands.*
import info.nightscout.androidaps.utils.wizard.BolusWizard
import info.nightscout.androidaps.utils.wizard.QuickWizardEntry
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        AppModule::class,
        ReceiversModule::class,
        ServicesModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApp> {

    fun injectProfileStore(profileStore: ProfileStore)
    fun injectPumpEnactResult(pumpEnactResult: PumpEnactResult)
    fun injectAPSResult(apsResult: APSResult)
    fun injectDetermineBasalResultSMB(determineBasalResultSMB: DetermineBasalResultSMB)
    fun injectDetermineBasalResultMA(determineBasalResultMA: DetermineBasalResultMA)
    fun injectDetermineBasalResultAMA(determineBasalResultAMA: DetermineBasalResultAMA)
    fun injectDetermineBasalAdapterSMBJS(determineBasalAdapterSMBJS: DetermineBasalAdapterSMBJS)

    fun injectCommandQueue(commandQueue: CommandQueue)
    fun injectCommandBolus(commandBolus: CommandBolus)
    fun injectCommandCancelExtendedBolus(commandCancelExtendedBolus: CommandCancelExtendedBolus)
    fun injectCommandCancelTempBasal(commandCancelTempBasal: CommandCancelTempBasal)
    fun injectCommandExtendedBolus(commandExtendedBolus: CommandExtendedBolus)
    fun injectCommandInsightSetTBROverNotification(commandInsightSetTBROverNotification: CommandInsightSetTBROverNotification)
    fun injectCommandLoadEvents(commandLoadEvents: CommandLoadEvents)
    fun injectCommandLoadHistory(commandLoadHistory: CommandLoadHistory)
    fun injectCommandLoadTDDs(commandLoadTDDs: CommandLoadTDDs)
    fun injectCommandReadStatus(commandReadStatus: CommandReadStatus)
    fun injectCommandSetProfile(commandSetProfile: CommandSetProfile)
    fun injectCommandCommandSMBBolus(commandSMBBolus: CommandSMBBolus)
    fun injectCommandStartPump(commandStartPump: CommandStartPump)
    fun injectCommandStopPump(commandStopPump: CommandStopPump)
    fun injectCommandTempBasalAbsolute(commandTempBasalAbsolute: CommandTempBasalAbsolute)
    fun injectCommandTempBasalPercent(commandTempBasalPercent: CommandTempBasalPercent)
    fun injectCommandSetUserSettings(commandSetUserSettings: CommandSetUserSettings)

    fun injectObjective0(objective0: Objective0)
    fun injectObjective1(objective1: Objective1)
    fun injectObjective2(objective2: Objective2)
    fun injectObjective3(objective3: Objective3)
    fun injectObjective5(objective5: Objective5)
    fun injectObjective6(objective6: Objective6)

    fun injectAutomationEvent(automationEvent: AutomationEvent)

    fun injectTrigger(trigger: Trigger)
    fun injectTrigger(triggerAutosensValue: TriggerAutosensValue)
    fun injectTrigger(triggerBg: TriggerBg)
    fun injectTrigger(triggerBolusAgo: TriggerBolusAgo)
    fun injectTrigger(triggerCOB: TriggerCOB)
    fun injectTrigger(triggerConnector: TriggerConnector)
    fun injectTrigger(triggerDelta: TriggerDelta)
    fun injectTrigger(triggerDummy: TriggerDummy)
    fun injectTrigger(triggerIob: TriggerIob)
    fun injectTrigger(triggerLocation: TriggerLocation)
    fun injectTrigger(triggerProfilePercent: TriggerProfilePercent)
    fun injectTrigger(triggerPumpLastConnection: TriggerPumpLastConnection)
    fun injectTrigger(triggerRecurringTime: TriggerRecurringTime)
    fun injectTrigger(triggerTempTarget: TriggerTempTarget)
    fun injectTrigger(triggerTime: TriggerTime)
    fun injectTrigger(triggerTimeRange: TriggerTimeRange)
    fun injectTrigger(triggerWifiSsid: TriggerWifiSsid)

    fun injectAction(action: Action)
    fun injectActionDummy(action: ActionDummy)
    fun injectActionLoopDisable(action: ActionLoopDisable)
    fun injectActionLoopEnable(action: ActionLoopEnable)
    fun injectActionLoopResume(action: ActionLoopResume)
    fun injectAction(action: ActionLoopSuspend)
    fun injectActionLoopSuspend(action: ActionNotification)
    fun injectActionProfileSwitch(action: ActionProfileSwitch)
    fun injectAction(action: ActionProfileSwitchPercent)
    fun injectActionProfileSwitchPercent(action: ActionSendSMS)
    fun injectActionStartTempTarget(action: ActionStartTempTarget)
    fun injectActionStopTempTarget(action: ActionStopTempTarget)

    fun injectElement(element: Element)
    fun injectElement(inputBg: InputBg)
    fun injectElement(inputButton: InputButton)
    fun injectElement(comparator: Comparator)
    fun injectElement(comparatorExists: ComparatorExists)
    fun injectElement(inputDateTime: InputDateTime)
    fun injectElement(inputDelta: InputDelta)
    fun injectElement(inputDouble: InputDouble)
    fun injectElement(inputDuration: InputDuration)
    fun injectElement(inputInsulin: InputInsulin)
    fun injectElement(inputLocationMode: InputLocationMode)
    fun injectElement(inputPercent: InputPercent)
    fun injectElement(inputProfileName: InputProfileName)
    fun injectElement(inputString: InputString)
    fun injectElement(inputTempTarget: InputTempTarget)
    fun injectElement(inputTimeRange: InputTimeRange)
    fun injectElement(inputTime: InputTime)
    fun injectElement(inputWeekDay: InputWeekDay)
    fun injectElement(labelWithElement: LabelWithElement)
    fun injectElement(staticLabel: StaticLabel)

    fun injectAutosensDate(autosensData: AutosensData)
    fun injectIobCobThread(iobCobThread: IobCobThread)
    fun injectIobCobOref1Thread(iobCobOref1Thread: IobCobOref1Thread)

    fun injectTreatment(treatment: Treatment)
    fun injectBgReading(bgReading: BgReading)

    fun injectNotification(notificationWithAction: NotificationWithAction)

    fun injectLoggerCallback(loggerCallback: LoggerCallback)
    fun injectBolusWizard(bolusWizard: BolusWizard)
    fun injectQuickWizardEntry(quickWizardEntry: QuickWizardEntry)

    fun injectAuthRequest(authRequest: AuthRequest)

    fun injectProfile(profile: Profile)
    fun injectGlucoseStatus(glucoseStatus: GlucoseStatus)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(mainApp: MainApp): Builder

        fun build(): AppComponent
    }
}